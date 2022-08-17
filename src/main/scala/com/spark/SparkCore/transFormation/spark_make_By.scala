package com.spark.SparkCore.transFormation

import org.apache.spark.{SparkConf, SparkContext}

object spark_make_By {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Demo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List(1, 6, 5, 4, 8, 79, 6, 4, 5, 23, 81, 2, 5, 42,
      4, 42, 5, 42, 42, 9, 7, 4, 56, 3, 2, 4, 8, 65), 3)

    val rdd3 = sc.makeRDD(1 to 20)
    //    union   对于两个数据集进行合并, 不去重
    val rdd4 = rdd.union(rdd3)
    //    subtract 去除两个rdd相同的元素,返回前一个rdd剩余的元素
    val rdd5 = rdd4.subtract(rdd)


    println(rdd5.collect().toBuffer)
    //    sort by 对传入的数据集进行排序,单双列数据集均可使用
    val data = rdd.sortBy(x => x).collect().toBuffer
    //    自定义排序,写一个样例类,继承Ordered 实现 Serializable 接口
    val rdd1 = sc.makeRDD(List(("jack", 21), ("tom", 19), ("lucy", 18), ("smith", 23)))
    val rdd2 = rdd1.sortBy(x =>
      student(x._1, x._2), false, 2)
    val arr = rdd2.glom().collect()
    //    for (elem <- arr) println(elem.toBuffer)


  }
}

//    自定义排序   样例类: case + 普通的类 定义变量时,常量的val可以省略, 但是变量的var 不能省略
case class student(var name: String, var age: Int) extends Ordered[student] with Serializable {
  //  that  -  this    升序排序
  override def compare(that: student): Int = that.age - this.age
}