package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_house {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd_house")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile(
      "E:\\游戏\\WeChat\\WeChat Files\\wxid_uck3f0xnfh5q22\\FileStorage\\File\\2022-08\\house.txt")
    val rdd1: RDD[Array[String]] = rdd.filter(_.split("\t")(5) != "").map(x => x.split("\t"))
    //    val array: Array[Array[String]] = rdd1.collect()
    //    for (elem <- array) {
    //      println(elem.toBuffer)
    //    }
    //    2.计算出东花市北里有多少条信息
    val rdd2: RDD[Array[String]] = rdd1.filter(x => if (x(1).contains("东花市北里")) true else false)
    println("2.东花市北里有" + rdd2.count() + "条信息")
    //    3.计算出住在朝阳区的所有人数
    val rdd3: RDD[Array[String]] = rdd1.filter(x => if (x(3).equals("朝阳")) true else false)
    val rdd31: RDD[Int] = rdd3.map(x => x(6).toInt)
    val cy: Int = rdd31.reduce(_ + _)
    println("3.住在朝阳区人数为:" + cy)

    //    println(s"3.朝阳区的所有人数为${count3}")
    //    4.计算出住在朝阳区的人数和住在西城区的人数哪个区的多,多多少?
    val rdd4: RDD[Array[String]] = rdd1.filter(x => if (x(3).equals("西城")) true else false)
    val rdd41: RDD[Int] = rdd4.map(x => x(6).toInt)
    val xc: Int = rdd41.reduce(_ + _)
    println(if (cy > xc) s"4.朝阳区的人数比西城区的人数多,多${cy - xc}" else s"4.西城区的人数比朝阳区的人数多,多${xc - cy}")
    //    5.计算出标题中含有胡同的所有租户有多少人
    val rdd5: RDD[Array[String]] = rdd1.filter(x => if (x(1).contains("胡同")) true else false)
    val con5: Int = rdd5.map(x => x(6).toInt).reduce(_ + _)
    println("5.标题中含有胡同的所有租户有:" + con5 + "人")
    //    6.计算出地铁1号线的租户有多少人?
    val rdd6: RDD[Array[String]] = rdd1.filter(x => if (x(5).contains("地铁1号线")) true else false)
    val con6: Int = rdd6.map(x => x(6).toInt).reduce(_ + _)
    println("6.地铁1号线的租户有:" + con6 + "人")
    //    7.计算出东城区王府井站有多少人?
    val rdd7: RDD[Array[String]] = rdd1.filter(x => if (x(3).contains("东城") && x(5).contains("王府井站")) true else false)
    val con7: Int = rdd7.map(x => x(6).toInt).reduce(_ + _)
    println("7.出东城区王府井站有:" + con7 + "人")
    //    8.计算出所有地区的人数有多少?
    val con8: Int = rdd1.map(x => x(6).toInt).reduce(_ + _)
    println("8.所有地区的人数为:" + con8)
    //    9.计算出各个地区的平均人数是多少?
    val rdd9: RDD[(String, Int)] = rdd1.map(x => (x(3), x(6).toInt))
    val sum: Int = rdd9.map(x => x._2).reduce(_ + _)
    val con: Long = rdd9.map(x => x._1).distinct().count()
    println("9.各个地区的平均人数是:" + sum / con)
    //    10.计算出地铁15号线比地铁1号线的人数多多少?
    val rdd10: RDD[Array[String]] = rdd1.filter(x => if (x(5).contains("地铁15号线")) true else false)
    val i15: Int = rdd10.map(x => x(6).toInt).reduce(_ + _)
    val rdd101: RDD[Array[String]] = rdd1.filter(x => if (x(5).contains("地铁1号线")) true else false)
    val i1: Int = rdd101.map(x => x(6).toInt).reduce(_ + _)
    println(if (i15 > i1) s"10.地铁15号线的人数比的地铁1号线人数多,多${i15 - i1}" else s"10.地铁1号线的人数比的地铁15号线人数多,多${i1 - i15}")
    //    11.计算出人数最多的前三个地区
    val rdd11: RDD[(String, Int)] = rdd1.map(x => (x(3), x(6).toInt))
    val rdd111: RDD[(String, Int)] = rdd11.reduceByKey(_ + _)
    val rdd112: RDD[(String, Int)] = rdd111.sortBy(x => x._2, false)
    val array: Array[(String, Int)] = rdd112.take(3)
    print("11.人数最多的前三个地区:")
    for (elem <- array) {
      print(elem + "\t")
    }
    println()
    //    12.计算出13号线的哪个站下的人最多?
    val rdd12: RDD[Array[String]] = rdd1.filter(x => if (x(5).contains("13号线")) true else false)
    val rdd121: RDD[(String, Int)] = rdd12.map(x => (x(5), x(6).toInt))
    val rdd122: RDD[(String, Int)] = rdd121.reduceByKey(_ + _)
    val rdd123: RDD[(String, Int)] = rdd122.sortBy(x => x._2, false)
    val str: String = rdd123.map(x => x._1).first()
    println("12.13号线人最多的站是:" + str)
    //    13.计算出望京地点14号线和15号线的下车总人数是多少?
    val rdd131: RDD[Array[String]] = rdd1.filter(x => if (x(4).equals("望京") && x(5).contains("14号线")) true else false)
    val rdd132: RDD[Array[String]] = rdd1.filter(x => if (x(4).equals("望京") && x(5).contains("15号线")) true else false)
    val i14: Int = rdd131.map(x => x(6).toInt).reduce(_ + _)
    val j15: Int = rdd132.map(x => x(6).toInt).reduce(_ + _)
    println("13.望京地点14号线和15号线的下车总人数是:" + (i14 + j15))
    //    14.计算出望京地点14号线占所有14号地铁站的人数比?
    val rdd141: RDD[Array[String]] = rdd1.filter(x => if (x(4).equals("望京") && x(5).contains("14号线")) true else false)
    val rdd142: RDD[Array[String]] = rdd1.filter(x => if (x(5).contains("14号线")) true else false)
    val i: Int = rdd141.map(x => x(6).toInt).reduce(_ + _)
    val j: Int = rdd142.map(x => x(6).toInt).reduce(_ + _)
    val d: Double = i.toDouble / j.toDouble
    val str1: String = d.formatted("%.2f")
    println("14.望京地点14号线占所有14号地铁站的人数比为:"+str1)
    //    15.计算出亚运村地点的人数和广渠门的人数总和?
    val rdd15: RDD[Array[String]] = rdd1.filter(x => if (x(4).toString.contains("亚运村") || (x(4).toString.contains("广渠门"))) true else false)
    val rdd151: RDD[Int] = rdd15.map(x => x(6).toInt)
    val sum1: Int = rdd151.reduce(_ + _)
    println("15.亚运村地点的人数和广渠门的人数总和为:"+sum1)
    //    16.计算出每个地区的地点人数最多的前3名?
    //    val rdd16: RDD[Array[String]] = rdd1.filter(x => if (x(3).equals("西城")) true else false)
    //    val rdd161: RDD[(String, Int)] = rdd16.map(x => (x(4), x(6).toInt))
    //    val rdd162: RDD[(String, Int)] = rdd161.reduceByKey(_ + _)
    //    val rdd163: RDD[(String, Int)] = rdd162.sortBy(x => x._2, false)
    //    val array1: Array[(String, Int)] = rdd163.take(3)
    //    print("西城区人数前三地点是:")
    //   array1.foreach(println)
    println("16.每个地区的地点人数最多的前3名:")
    val array1: Array[String] = rdd1.map(x => (x(3))).distinct().collect()
    for (elem <- array1) {
      val rdd16: RDD[Array[String]] = rdd1.filter(x => if (x(3).equals(elem)) true else false)
      val rdd161: RDD[(String, Int)] = rdd16.map(x => (x(4), x(6).toInt))
      val rdd162: RDD[(String, Int)] = rdd161.reduceByKey(_ + _)
      val rdd163: RDD[(String, Int)] = rdd162.sortBy(x => x._2, false)
      val array: Array[(String, Int)] = rdd163.take(3)
      print(elem+"区人数前三地点是:")
      array.foreach(println)
    }
  }
}


