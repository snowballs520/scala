package com.spark.SparkCore.collect

import java.util

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.JavaConverters._
import scala.collection.mutable

/**
 * 分布式共享只写变量,-----task之间不能读数据
 * 定义累加器
 * 累加器能够返回执行rdd操作时需要返回的变量
 * 普通变量不能够进行累加计算
 */
object LeiJiaQi {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("LeiJiaQi").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //    累加器   accumulator
    val rdd = sc.textFile("D:\\JavaProject/dir/aaa.txt")
    val acc = sc.accumulator(0)
    val rdd1 = rdd.filter(f = x => {
      val length = x.length
      if (length == 0) {
        acc += 1
      }
      true
    })
    rdd1.foreach(println)
    println(acc.value)

    var arr = Array(1,2,2,3,4,5,6,"djskad")

    val str = arr.mkString("===")
    val str1 = arr.mkString("[", "-----", "]")
    println(str1)
    println(str)

  }
}

/**
 * 自定义累加器
 */
object LogAccumulator extends AccumulatorV2[String, Set[String]] {
  private val set = new util.HashSet[String]()

  override def isZero: Boolean = {
    set.isEmpty
  }

  override def copy(): AccumulatorV2[String, Set[String]] = {
    ???
  }

  override def reset(): Unit = {set.clear()}

  override def add(v: String): Unit = ???

  override def merge(other: AccumulatorV2[String, Set[String]]): Unit = ???

  override def value: Set[String] = ???
}