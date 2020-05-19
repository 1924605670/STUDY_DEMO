package com.czh.scala.study_spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object Spark_Session {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark_sql").setMaster("local[*]")
    // 创建 sparkSession
    val spark = SparkSession.builder().config(conf).getOrCreate()
    // sparkContext 也可以通过sparkSession获取
    //     val sc = sparkSession.sparkContext

    import spark.implicits._

    //    val dataFrame: DataFrame = spark.read.json("/Users/chengzhihua/IdeaProjects/STUDY_DEMO/Spark_Demo/in/user.json")
    //    dataFrame.printSchema()
    //    dataFrame.show()

    // dataFrame 转 DataSet
    //    val dataSet: Dataset[Person] = dataFrame.as[Person]
    //    dataSet.printSchema()
    //    dataSet.show()

    // rdd 转 DataSet
    val rdd: RDD[Person] = spark.sparkContext.textFile("Spark_Demo/in/person").map(x => {
      val ss = x.split(",")
      Person(ss(0), ss(1).toLong)
    })
    val set = rdd.toDS()
    set.printSchema()
    set.show()


    set.createTempView("person")
    spark.sql("select * from person where age > 22").show()

    spark.close()

  }

}

case class Person(name: String, age: Long) extends Serializable


