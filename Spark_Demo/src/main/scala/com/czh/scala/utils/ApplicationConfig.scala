package com.czh.scala.utils

import java.io

import org.apache.kafka.common.serialization.StringDeserializer
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.apache.kafka.clients.consumer.ConsumerConfig

object ApplicationConfig {
  @transient private var instance: HikariDataSource = _

  val props = ConfigFactory.load("application.conf")


  /**
    * 加载关系数据库配置
    *
    * @param tableName
    * @return
    */
  def getDBOptions(tableName: String): Map[String, String] = {
    val jdbcMapv = Map("url" -> props.getString("datasource.mysql.url"),
      "user" -> props.getString("datasource.mysql.username"),
      "password" -> props.getString("datasource.mysql.password"),
      "dbtable" -> tableName,
      "driver" -> props.getString("datasource.mysql.driver"))
    return jdbcMapv
  }


  def getEsOptions(): Map[String, String] = {

    val esMap = Map("es.nodes" -> props.getString("application.es.addr_http"),
      "pushdown" -> "true",
      "es.mapping.date.rich" -> "false",
      "double.filtering" -> "false",
      "es.scroll.size" -> "10000",
      "es.scroll.keepalive" -> "10m",
      "es.port" -> "9200",
      "es.nodes.wan.only" -> "true")
    return esMap
  }


  def getDataSourceInstance: HikariDataSource = {
    if (instance == null) {
      try {
        val config = new HikariConfig
        config.setJdbcUrl(props.getString("datasource.mysql.url"))
        config.setUsername(props.getString("datasource.mysql.username"))
        config.setPassword(props.getString("datasource.mysql.password"))
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        instance = new HikariDataSource(config)
      } catch {
        case ex: Exception => ex.printStackTrace()
      }
    }
    instance
  }

  def getKafkaConf() = {
    val kafkaParams = Map(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> props.getString("kafka.bootstrapServers"),
      ConsumerConfig.GROUP_ID_CONFIG -> props.getString("kafka.groupId"),
      ConsumerConfig.MAX_POLL_RECORDS_CONFIG -> props.getString("kafka.maxPoll"),
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer]
    )
     kafkaParams
  }


}
