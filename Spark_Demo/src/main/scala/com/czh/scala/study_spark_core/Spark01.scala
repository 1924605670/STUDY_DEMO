package com.czh.scala.study_spark_core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Spark01")
    val sc = new SparkContext(conf)


    // 1 创建RDD makeRDD底层实现是parallelize()
    val rdd = sc.makeRDD(1 to 10)
    val rdd2 = sc.parallelize(List(1, 2, 3, 4))

    // 2 传递的partitions参数是最小分区数，实际上是按照hadoop读取文件的分片规则
    val rdd3 = sc.textFile("Spark_Demo/src/main/words", 2)

    val rdd4 = rdd.mapPartitionsWithIndex((num, datas) => {
      datas.map((_, "分区号：" + num))
    })

    //    rdd4.collect().foreach(println)
    //    rdd2.collect().foreach(println)
    //    rdd3.collect() foreach (println)


    // groupBy(f) 根据函数返回值分类

    val rdd5 = sc.parallelize(1 to 100, 5)
    val rdd6: RDD[(Int, Iterable[Int])] = rdd5.groupBy(_ % 2)
    rdd5.filter(_ % 2 == 0).collect().foreach(print)
    rdd6.collect().foreach(println)


    val sortRdd = sc.parallelize(List(1,2,3,4,3,2,1))
    sortRdd.sortBy(x=>x,false).collect().foreach(println)




  }

}
