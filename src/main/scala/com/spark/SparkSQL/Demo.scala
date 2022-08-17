package com.spark.SparkSQL

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("b").setMaster("local[2]")
//   链接hive的时候需要加上 enableHiveSupport
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val df = spark.read.json("dir/a.json")




//    import spark.implicits._
//    import spark.sql
//
//    val df = spark.sql("select * from emp")
//    df.show()




    /**    获取sparkSession对象

    //    val df = spark.read.text("dir/aa.txt")

    df1.show()
    // df.show()
     */


  }
}
