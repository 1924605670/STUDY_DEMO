package com.czh.scala.study_spark_core

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 累加器
  */
object Spark_Accum {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("accum")
    val sc = new SparkContext(conf)


    // 自带累加器
    //    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7), 2)
    //    // 累加器
    //    val accumulator = sc.longAccumulator
    //
    //    rdd.foreachPartition(x => {
    //      x.foreach {
    //        case i => {
    //          accumulator.add(i)
    //        }
    //      }
    //    })
    //
    //    print("======" + accumulator.value)
    //

    // 自定义累加器
    val rdd: RDD[String] = sc.makeRDD(List("hadoop", "hbase", "hive", "spark"), 2)
    val wordAccumulator = new WordAccumulator
    // 注册累加器到spark
    sc.register(wordAccumulator)
    rdd.foreachPartition(x => {
      x.foreach {
        case i => {
          wordAccumulator.add(i)
        }
      }
    })

    print("diy accumulator === " + wordAccumulator.value)
    sc.stop()

  }
}

// 自定义累加器
class WordAccumulator extends AccumulatorV2[String, util.ArrayList[String]] {
  private val list = new util.ArrayList[String]()

  override def isZero: Boolean = list.isEmpty

  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = new WordAccumulator

  override def reset(): Unit = list.clear()

  override def add(v: String): Unit = {
    if (v.contains("h")) {
      list.add(v)
    }
  }

  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = list.addAll(other.value)

  override def value: util.ArrayList[String] = list
}


