package com.study.flink.conf

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig

object Conf {

  val kafkaConf: Properties = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "server1:9092")
    properties.setProperty("group.id", "test")
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    properties
  }


}
