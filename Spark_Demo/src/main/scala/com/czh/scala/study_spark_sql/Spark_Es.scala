package com.czh.scala.study_spark_sql

import com.czh.scala.utils.ApplicationConfig
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Es {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster(ApplicationConfig.props.getString("application.spark.master")).setAppName("Spark_Es")

    val sparkSession = SparkSession.builder().config(conf).getOrCreate()

    val sqlContext = sparkSession.sqlContext

//    sqlContext.read.format("jdbc").options(ApplicationConfig.getDBOptions("dev_t_base")).load.createTempView("dev_t_base")

    //    sqlContext.sql("select * from dev_t_base_ref ")
    //      .write
    //      .format("org.elasticsearch.spark.sql")
    //      .options(ApplicationConfig.getEsOptions())
    //      .mode(SaveMode.Append)
    //      .save("dev_t_base_ref/docs")

//    sqlContext.read.format("jdbc").options(ApplicationConfig.getDBOptions("base_t_site")).load.createTempView("base_t_site")


//    sqlContext.sql("select dev.*,site.dwmc from dev_t_base dev left join base_t_site site on dev.dwbh = site.dwbh")
//      .write
//      .format("org.elasticsearch.spark.sql")
//      .options(ApplicationConfig.getEsOptions())
//      .mode(SaveMode.Append)
//      .save("device_site/docs")

    val frame: DataFrame = sqlContext.read.format("org.elasticsearch.spark.sql").options(ApplicationConfig.getEsOptions()).load("device_site/docs")

    frame.show()
    sparkSession.close()




  }


}
