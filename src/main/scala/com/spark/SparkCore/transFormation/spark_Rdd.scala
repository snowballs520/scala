package com.spark.SparkCore.transFormation

import org.apache.spark.{SparkConf, SparkContext}

object spark_Rdd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("diJia").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(1 to 5)
    val rdd1 = sc.makeRDD(3 to 8)

    //     intersection   原rdd与新rdd的交集
    val rdd2 = rdd.intersection(rdd1)
    println(rdd2.collect().toBuffer)
    //    cartesian   两个rdd的笛卡尔积
    val rdd3 = rdd1.cartesian(rdd)
    println(rdd3.collect().toBuffer)
    //    join    链接两个(k,v)类型的 rdd
    //     cogroup   适用于kv类型的rdd   在类型为(K,V)和(K,W)的RDD上调用，返回一个(K,(Iterable<V>,Iterable<W>))类型的RDD
    val rdd11 = sc.makeRDD(Array((1, "张三"), (2, "李四"),(3,"dijia")))
    val rdd22 = sc.makeRDD(Array((1, 100), (2, 20), (3, 50)))
    val rdd33 = rdd11.cogroup(rdd22)
    val array = rdd33.glom().collect()
    for (elem <- array) println(elem.toBuffer)
    println("==============================")
    rdd33.foreach(str=>{
      println("id\t"+str._1)
      println("name\t"+str._2._1)
      println("score\t"+str._2._2)
    })

  }
}
