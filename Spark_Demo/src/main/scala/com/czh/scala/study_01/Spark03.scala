package com.czh.scala.study_01

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}


/**
  * aggregate
  */
object Spark03 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // partitionBy 的对象rdd必须是 KV对

    // 当 rdd是KV类型的时候，会被隐式转换成pariRdd，拥有了pariRdd的方法了PairRDDFunctions
    val rdd: RDD[(String, Int)] = sc.parallelize(List(("a", 1), ("b", 2), ("a", 3), ("c", 1), ("c", 2), ("d", 6), ("a", 8)), 2)
    // 取每个分区内相同key最大的数，然后相加
    // 第一个参数是初始比较的值，第二个方法是分区内执行的方法 第三个是分区间执行的方法
    val res: RDD[(String, Int)] = rdd.aggregateByKey(0)(math.max(_, _), _ + _)


    // foldByKey 用于分区内和分区间相同计算
    val foldRes: RDD[(String, Int)] = rdd.foldByKey(0)(_ + _)


    // combineByKey 第一个函数是改变 v 初始值结构 的方法
    val value: RDD[(String, (Int, Int))] = rdd.combineByKey(v => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    foldRes.foreach(println)

    val d: RDD[(String, Double)] = value.map { case (k, v) => (k, v._1 / v._2.toDouble) }

    d.foreach(println)

//    res.foreach(println)
    // 只对v进行操作
    rdd.mapValues(v => v+"||||||").foreach(println)



  }

}


