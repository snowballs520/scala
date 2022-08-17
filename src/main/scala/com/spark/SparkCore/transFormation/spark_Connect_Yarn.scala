package com.spark.SparkCore.transFormation

import org.apache.spark.{SparkConf, SparkContext}

//    spark   在Yarn集群上运行
object spark_Connect_Yarn {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sc = new SparkContext(conf.setMaster("local[*]").setAppName("demo"))



  }
}
