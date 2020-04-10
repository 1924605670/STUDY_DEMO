package com.czh.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Hello world!
  *
  */
object App {
  println("Hello World!")

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("FirstWordCount").setMaster("spark://server1:7077")
    val sc = new SparkContext(conf)
    sc.textFile(args(0))
      .flatMap(x => x.split(" "))
      .map(w => (w, 1))
      .reduceByKey(_+_)
      .saveAsTextFile(args(1));
  }
}
