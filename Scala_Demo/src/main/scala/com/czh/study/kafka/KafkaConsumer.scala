package com.czh.study.kafka

import java.time.Duration
import java.util.concurrent.Executors
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

object KafkaConsumer {
  val ZK_CONN = "server1:2181,server2:2181,server3:2181"
  val GROUP_ID = "scala.group"

  def main(args: Array[String]): Unit = {
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer(createConfig())
    //订阅主题，可以订阅多个
    //    consumer.subscribe(util.Collections.singletonList("scala_message"))

    val partition: TopicPartition = new TopicPartition("scala_message", 0)
    consumer.assign(Collections.singleton(partition))
    // 从头开始消费
    //    consumer.seekToBeginning(Collections.singleton(partition))
    consumer.seekToEnd(Collections.singleton(partition))


    // 从指定offset消费只能一个一个设置
    //    consumer.seek(partition, 30l)

    // 开始消费

    Executors.newSingleThreadExecutor.execute(new Runnable {
      override def run(): Unit = {
        while (true) {
          val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofSeconds(1000))
          val value = records.iterator()
          while (value.hasNext) {
            val msg: ConsumerRecord[String, String] = value.next()
            System.out.println("Received message: (" + msg.key() + ", " + msg.value() + ") at offset " + msg.offset())
          }
        }
      }
    })


  }

  def createConfig(): Properties = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "server1:9092,server2:9092,server3:9092") // 设置kafaka集群地址
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "scala-group")
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true") // 设置自动提交
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    // 可以通过以下配置来设定从哪里开始消费，从最开始和最新
    //        p.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //        p.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    return props
  }

}
