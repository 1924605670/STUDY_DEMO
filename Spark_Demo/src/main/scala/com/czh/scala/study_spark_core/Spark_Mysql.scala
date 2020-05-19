package com.czh.scala.study_spark_core

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Mysql {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("mysql_spark")
    val sc = new SparkContext(conf)

    val driver = "com.mysql.jdbc.Driver"
    val username = "bcht"
    val passwd = "Bcht123"
    val jdbcurl = "jdbc:mysql://10.20.10.5:3308/its?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2b8"
    val sql = "select * from sys_role_menu where roleID > ? and roleID < ?"
    val rdd = new JdbcRDD(sc,
      () => {
        Class.forName(driver)
        java.sql.DriverManager.getConnection(jdbcurl, username, passwd)
      },
      sql, 10, 200, 2,
      (rs) => {
        println(rs.getString(2))
      })
    rdd.collect()


    // 插入的时候 使用 foreachPartition 减少链接

  }
}
