package com.czh.scala.study_spark_core

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 广播变量  性能调优
  */
object Spark_Broadcast {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("bord")
    val sc = new SparkContext(conf)
    val a: Int = 10
    val broadCast: Broadcast[Int] = sc.broadcast(a)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 4)

    rdd.foreachPartition(x => {
      var sum = 0
      x.foreach {
        case i => {
          sum = sum + i + broadCast.value
        }
      }
      println("==" + sum)
    })

    sc.stop()

  }
}
