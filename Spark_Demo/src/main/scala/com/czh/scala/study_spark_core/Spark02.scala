package com.czh.scala.study_spark_core

import org.apache.spark.{Partitioner, SparkConf, SparkContext}


/**
  * 自定义分区案例
  */
object Spark02 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // partitionBy 的对象rdd必须是 KV对

    // 当 rdd是KV类型的时候，会被隐式转换成pariRdd，拥有了pariRdd的方法了PairRDDFunctions
    sc.parallelize(List((1, "a"), (2, "b"), (3, "e"), (1, "a"), (2, "b"), (3, "e"))).partitionBy(new MyPartition(3))
      .saveAsTextFile("out")

    sc.stop()

  }


  // 自定义分区类
  class MyPartition(parts: Int) extends Partitioner {
    override def numPartitions: Int = {
      parts
    }

    override def getPartition(key: Any): Int = key match {
      case key: Int => 0
      case _ => 2
    }
  }

}


