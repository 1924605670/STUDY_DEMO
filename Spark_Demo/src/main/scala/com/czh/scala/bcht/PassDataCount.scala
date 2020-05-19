package com.czh.scala.bcht

import cn.hutool.core.date.DateUtil
import cn.hutool.core.lang.UUID
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.czh.scala.utils.ApplicationConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.elasticsearch.spark.rdd.EsSpark
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization


/**
  * 过车数据统计分析
  * 从 kafka接入数据
  * 1.统计每分钟过车数据量
  */

object PassDataCount {

  def main(args: Array[String]): Unit = {

    val topicName = "ITS_QUEUE_PASS_TO_HK_KAFKA"

    val sparkConf = new SparkConf().setAppName("PassDataCount")
      .setMaster(ApplicationConfig.props.getString("application.spark.master"))
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .set("spark.streaming.stopGracefullyOnShutdown", "true")
      .set("es.index.auto.create", "false")
      .set("es.nodes", "server1,server2,server3")
      .set("es.port", "9200")
      .set("es.index.auto.create", "true")

    val streamingContext = new StreamingContext(sparkConf, Seconds(10))

    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    val sqlContext = sparkSession.sqlContext


    streamingContext.checkpoint("Spark_Demo/cp")

    val kafkaStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe(Set(topicName), ApplicationConfig.getKafkaConf()))


    val esConfig = scala.collection.mutable.Map("es.resource.write" -> "passcount/doc", "es.mapping.id" -> "id", "es.write.operation" -> "upsert")

    kafkaStream.foreachRDD(rdd => {
      val res: RDD[String] = rdd.map {
        case x => {
          val bean = JSON.parseObject(x.value(), classOf[PassBean])
          (bean.sbxh, 1)
        }
      }.reduceByKey(_ + _).map {
        case (a, b) => {
          val countBean = PassCountBean(UUID.randomUUID().toString(), a, b, DateUtil.now())
          // 样例类无法直接转换成json串，需要引入json4s的隐式转换才可以正常转换成
          implicit val formats = DefaultFormats
          val str = Serialization.write(countBean)
          str
        }
      }

      EsSpark.saveJsonToEs(res,esConfig)

    })

    streamingContext.start()
    streamingContext.awaitTermination()


  }
}
  case class PassBean(gcbh: String, sbxh: String, jgsjStr: String, hphm: String, hpzl: String, hpys: String, cwkc: Double, clys: String, cllx: String, sd: Double, cdbh: Int, fx: String, sjlx: String, lsh: String, dataList: String, jgsj: String, hjsj: String, scsj: String, ffsj: String, rksj: String, jrsj: String) extends Serializable

  case class PassCountBean(id: String, sbxh: String, count: Int, startTime: String) extends Serializable

