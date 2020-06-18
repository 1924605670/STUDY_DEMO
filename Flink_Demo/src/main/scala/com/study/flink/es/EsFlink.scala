package com.study.flink.es

import java.util

import com.study.flink.conf.Conf
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests

object EsFlink {


  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val ds: DataStream[String] = env.addSource(new FlinkKafkaConsumer011[String]("kafkatest", new SimpleStringSchema(), Conf.kafkaConf))

    val res: DataStream[WordCount] = ds.flatMap(x => {
      x.split(" ")
    }).map((_, 1)).keyBy(0).sum(1).map(x => WordCount(x._1, x._2))

    val hosts = new util.ArrayList[HttpHost]()
    hosts.add(new HttpHost("10.20.10.164", 19300, "http"))

    val esSinkFun = new ElasticsearchSinkFunction[WordCount] {
      override def process(wordCount: WordCount, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
        val dataSource = new util.HashMap[String, Int]()
        dataSource.put(wordCount.key, wordCount.value)
        val request = Requests.indexRequest().index("flink_es").`type`("doc").source(dataSource)
        requestIndexer.add(request)
        print("ok")
      }
    }

    val esSinkBuilder: ElasticsearchSink.Builder[WordCount] = new ElasticsearchSink.Builder[WordCount](hosts, esSinkFun)

    res.addSink(esSinkBuilder.build())

    env.execute(" flink es sink")


  }


}

case class WordCount(key: String, value: Int)
