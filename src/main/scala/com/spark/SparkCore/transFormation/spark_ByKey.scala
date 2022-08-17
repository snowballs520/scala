package com.spark.SparkCore.transFormation

import org.apache.spark.{SparkConf, SparkContext}

object spark_ByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("lei").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List("tom","jack","lucy","smith","lucy"))
    val rdd1 = rdd.map((_, 1))
//    reduceByKey  将相同的key进行分组,聚合对应的value
    val rdd2 = rdd1.reduceByKey(_ + _)



  }
}
