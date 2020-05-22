package com.czh.study.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

/**
  * kafka消息生产者
  * 高级API自动管理offset等
  */
object KafkaProduce {

  def main(args: Array[String]): Unit = {
    val BROKER_LIST = "server1:9092,server2:9092,server3:9092"
    val topic = "scala_message"
    val props = new Properties()
    props.put("metadata.broker.list", BROKER_LIST)
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.czh.study.kafka.KafkaPartition")
    val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer(props)

    for (i <- 1 to 10) {
      kafkaProducer.send(new ProducerRecord(topic, "message" + i, "hello" + i))
    }

    kafkaProducer.close

  }

}
