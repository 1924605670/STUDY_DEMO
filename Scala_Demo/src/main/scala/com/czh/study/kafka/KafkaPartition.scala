package com.czh.study.kafka

import java.util

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

/**
  * 自定义kafka分区
  */
class KafkaPartition extends Partitioner {

  // 多个分区数 必须在创建主题的时候指定分区数。
  // 可以通过命令修改主题分区数： ./kafka-topics.sh --alter --zookeeper server1:2181 --topic scala_message --partitions 3
  override def partition(s: String, key: Any, keyBytes: Array[Byte], value: Any, valueBytes: Array[Byte], cluster: Cluster): Int = {
    val i: Int = key.toString.substring(7).toInt % 3
    i
  }

  override def close(): Unit = {
  }

  override def configure(map: util.Map[String, _]): Unit = {
  }
}


