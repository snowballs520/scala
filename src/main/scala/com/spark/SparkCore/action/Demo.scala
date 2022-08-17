package com.spark.SparkCore.action

import org.apache.spark.{SparkConf, SparkContext}
// reduce   用于聚合rdd内的内蓉,单双列数据集均可使用
object Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("ou")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(1 to 10, 2)
    val rdd1 = sc.makeRDD(Array(("a", 1), ("b", 2), ("c", 3)))
    val str = rdd1.reduce((x, y) => (x._1 + y._1, x._2 + y._2))
    println(str)


    val i = rdd.reduce(_ + _)
    println(i)
  }
}
