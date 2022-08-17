package com.spark.SparkCore.transFormation

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext, TaskContext}
// partitionBy   对rdd进行分区,原有的rdd跟现在的rdd是一样的话,就不分区  只能用于双列数据集

object spark_TransFormation_Demo1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Demo").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List("hello", "jason", "what", "are", "you", "doing", "hi", "jason", "do",
      "you", "eat", "dinner", "hello", "jason", "do", "you", "have", "some", "time", "hello", "jason", "time", "do",
      "you", "jason", "jason"), 4)
    val rdd1 = rdd.flatMap(_.split(",").map((_, 1)))

    // repartition 重分区10个  根据分区数,重新通过网络随机洗牌所有的数据
    rdd.repartition(3)
    rdd1.foreachPartition(num => {
      println("分区号" + TaskContext.getPartitionId())
      num.foreach(println)
    })
    println("************************************")

    //    重新定义分区 (默认使用hash分区, 可自定义分区)
    val rdd2 = rdd1.partitionBy(new HashPartitioner(10))
    rdd2.foreachPartition(num => {
      println("分区" + TaskContext.getPartitionId())

    })
    //    自定义分区 需写一个自定义分区类,然后调用自定义分区
    val myPartition = new myPartition(6)
    val rdd3 = rdd1.partitionBy(myPartition)
    val array = rdd3.glom().collect()
    for (elem <- array)
      println(elem.toBuffer)
println("=====================================")
    //    缩减分区数   coalesce

    val rdd4 = rdd.coalesce(3)
    val arr = rdd4.glom().collect()
    for (elem <- arr) println(elem.toBuffer)




  }
}


// 自定义分区
class myPartition(var num: Int) extends Partitioner {
  override def numPartitions: Int = num

  override def getPartition(key: Any): Int = {
    if (key.toString.length <= 2) 0 else 1
  }
}