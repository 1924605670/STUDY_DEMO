package com.czh.scala.study_spark_streaming


import com.alibaba.fastjson.JSON
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_Kafka {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_Kafka").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val streamingContext = new StreamingContext(conf, Seconds(5))
    streamingContext.checkpoint("Spark_Demo/cp")

    val bootstrapServers = "server1:9092,server2:9092,server3:9092"
    val groupId = "kafka-test-group"
    val topicName = "ITS_QUEUE_PASS_TO_HK_KAFKA"
    val maxPoll = 500

    val kafkaParams = Map(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> bootstrapServers,
      ConsumerConfig.GROUP_ID_CONFIG -> groupId,
      ConsumerConfig.MAX_POLL_RECORDS_CONFIG -> maxPoll.toString,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer]
    )

    val kafkaDStram: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(streamingContext, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set(topicName), kafkaParams))

    //     单窗口模式
    val value: DStream[(Int, Int)] = kafkaDStram.map {
      case x => {
        val value: Bean = JSON.parseObject(x.value(), classOf[Bean])
        value.age
      }
    }.map((_, 1)).reduceByKey(_ + _)
    value.print()


    //     窗口模式kafkastreaming 需要在窗口之前先map或者flatmap一下，否则会报错
    val dStream = kafkaDStram.map(x => {
      x.value()
    }).window(Seconds(10), Seconds(5))
    val res: DStream[(Int, Int)] = dStream.map(t => {
      val bean: Bean = JSON.parseObject(t, classOf[Bean])
      (bean.age, 1)
    }).reduceByKey(_ + _)

    res.print()


    streamingContext.start()
    streamingContext.awaitTermination()
  }

}

case class Bean(name: String, age: Int) extends Serializable
