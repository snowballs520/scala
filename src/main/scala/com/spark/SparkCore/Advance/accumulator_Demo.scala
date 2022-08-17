package com.spark.SparkCore.Advance

import org.apache.spark.{SparkConf, SparkContext}

object accumulator_Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("a")
    val sc = new SparkContext(conf)
    val arr = Array(1, 2, 3, 4, 5)
    val d = sc.longAccumulator("di")
     sc.makeRDD(arr).foreach(x=>d.add(x))
    println(d.value)

  }
}
