package com.spark.SparkCore.collect

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object collectJDBC {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("o").setMaster("local[*]")
    val sc = new SparkContext(conf)
//    new JdbcRDD(sc,()=>{
//      Class.forName("com.mysql.jdbc.Driver").newInstance()

//    })
  }
}
