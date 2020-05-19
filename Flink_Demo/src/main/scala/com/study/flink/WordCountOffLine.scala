package com.study.flink

import org.apache.flink.api.scala._

/**
  * 离线分析 数据结构是DataSet
  *
  */
object WordCountOffLine {


  def main(args: Array[String]): Unit = {
    val environment = ExecutionEnvironment.getExecutionEnvironment
    val dataSet: DataSet[String] = environment.readTextFile("Flink_Demo/in/a")
    dataSet.flatMap(_.split(" ")).map((_,1)).groupBy(0).sum(1).print()

  }

}
