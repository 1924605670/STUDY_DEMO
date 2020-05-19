package com.czh.scala.study_spark_streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_WordCount")

    val streamingContext = new StreamingContext(conf, Seconds(5))

    val stream: ReceiverInputDStream[String] = streamingContext.socketTextStream("server1", 9999)

    val wordCount: DStream[(String, Int)] = stream.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    wordCount.print()

    streamingContext.start()

    streamingContext.awaitTermination()


  }
}
