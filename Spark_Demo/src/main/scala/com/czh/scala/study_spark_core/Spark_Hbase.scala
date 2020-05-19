package com.czh.scala.study_spark_core

import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{CellUtil, HBaseConfiguration}
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Hbase {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("spark_hbase")
    val sc = new SparkContext(conf)

    // hbase查询数据
    // 创建hbase配置
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set(TableInputFormat.INPUT_TABLE, "user")

    val res: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(
      hbaseConf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )
    res.foreach {
      case (rowkey, rs) => {
        val cells = rs.rawCells()
        cells.foreach(x => {
          println(Bytes.toString(rowkey.get()) + "==" + Bytes.toString(CellUtil.cloneValue(x)))
        })
      }
    }

    // hbase 新增数据

    //    val listRdd: RDD[(String, String)] = sc.makeRDD(List(("1003", "wangwu"), ("1004", "赵六")))
    //    val result: RDD[(ImmutableBytesWritable, Put)] = listRdd.map {
    //      case (rowKey, value) => {
    //        val put = new Put(Bytes.toBytes(rowKey))
    //        put.addImmutable(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(value))
    //        (new ImmutableBytesWritable(Bytes.toBytes(rowKey)), put)
    //      }
    //    }
    //    val jobConf = new JobConf(hbaseConf)
    //    jobConf.setOutputFormat(classOf[TableOutputFormat])
    //    jobConf.set(TableOutputFormat.OUTPUT_TABLE,"user")
    //    result.saveAsHadoopDataset(jobConf)

    sc.stop()

  }


}
