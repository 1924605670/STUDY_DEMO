package com.study.flink.kafka

import com.study.flink.conf.Conf
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}

object KafkaFlink {


  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)


    val ds: DataStream[String] = env.addSource(new FlinkKafkaConsumer011[String]("kafkatest", new SimpleStringSchema(), Conf.kafkaConf))

    val res: DataStream[(String, Int)] = ds.flatMap(_.split(" ")).map((_, 1)).keyBy(0).sum(1)

    val sink: DataStream[String] = res.map(x => WordCount(x._1,x._2).toString)

    sink.addSink(new FlinkKafkaProducer011[(String)]("kafkatest1",new SimpleStringSchema(),Conf.kafkaConf))

    res.print()

    env.execute()


  }



}

case class WordCount(word:String ,count:Int)
