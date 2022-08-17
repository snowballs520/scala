package com.spark.SparkCore.transFormation

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object spark_TransFormation {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("demo")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 9, 3)
    //   glom   将每一个分区形成一个数组,形成新的rdd
    for (elem <- rdd.glom().collect()) {
      //      println(elem.toBuffer)
    }

    //   mapPartitions  映射数据集中的每一个元素,每一个分区映射一次
    val rdd1 = rdd.mapPartitions(x => {
      val list = ListBuffer[Int]()
      while (x.hasNext) {
        val i = x.next()
        list += (i + 0)
      }
      list.iterator
    })
    //    println(rdd1.collect().toBuffer)


    //    map
    /** val rdd1 = rdd.map(_*2)
     * println(rdd1.collect().toBuffer) */

    //    flatMap  类似于map ,但是每一个输入的元素都可以被映射成0或多个元素;
    val rdd2 = rdd.flatMap(1 to _)
    //    println(rdd2.collect().toBuffer)


    //    filter   过滤数据集中的元素, 符合条件的返回
    val rdd3 = rdd.filter(x => x % 2 == 0)
    //    println(rdd3.collect().toBuffer)

    //    mapPartitionWithIndex
    val rdd4 = rdd.mapPartitionsWithIndex((x, iter) => {
      //      在创建listBuffer集合时  注意写括号  要不然没有返回类型
      val list = ListBuffer[String]()
      while (iter.hasNext) {
        val i = iter.next()
        list += ((i + 0) + "|--|" + x)
      }
      list.iterator
    })
    //    println(rdd4.collect().toBuffer)

    /**
     * sample  (withReplacement, fraction, seed)
     * 以指定的随机种子随机抽样出数量为fraction的数据,withReplacement表示是抽出的数据是否放回，
     * true为有放回的抽样，false为无放回的抽样，seed用于指定随机数生成器种子。
     **/

    val rdd5 = rdd.sample(true, 0.5, 6)
//    println(rdd5.collect().toBuffer)


//    distinct   对原rdd进行去重,可以通过指定numTask个数决定执行其的并行任务的个数, 默认情况下 8 个.
    val list = ListBuffer(1, 2, 1, 3, 5, 4, 5, 4, 8, 7, 9, 2, 12, 4, 42, 1, 2, 4, 1, 25, 4, 6)
    val rdd6 = sc.makeRDD(list)
    val rdd8 = rdd6.distinct()
    println(rdd8.collect().toBuffer)
    println("--------")
    val rdd7 = rdd6.distinct(8)
    println(rdd7.collect().toBuffer)

  }
}
