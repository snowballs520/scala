package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{Accumulator, SparkConf, SparkContext}

object Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("demo")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile(
      "E:\\游戏\\WeChat\\WeChat Files\\wxid_uck3f0xnfh5q22\\FileStorage\\File\\2022-08\\house.txt")
    val rdd2: RDD[Array[String]] = rdd.filter(_.split("\\t")(5) != "").map(x => x.split("\\t"))

    /**
     * 清洗
     */

    //    2、计算出东花市北里有多少条信息
    val value2: RDD[Array[String]] = rdd2.filter(x => if (x(1).contains("东花市北里")) true else false)
    println("2.东花市北里有" + value2.count() + "条信息")
    //    3、计算出住在朝阳区的所有人数
    val value3 = rdd2.filter(x => if (x(3).equals("朝阳")) true else false)

    /**
     *              累加器     count3
     */
    var count3: Accumulator[Int] = sc.accumulator(0)
    value3.foreach(x => {
      count3 += x(6).toInt
    })
    println(s"3.朝阳区的所有人数为${count3}")
    // 4、计算出住在朝阳区的人数和住在西城区的人数哪个区的多，多多少？
    val value41 = rdd2.filter(x => if (x(3).toString.equals("朝阳")) true else false)
    var count41: Accumulator[Int] = sc.accumulator(0)
    value41.foreach(x => {
      count41 += x(6).toInt
    })
    val value42 = rdd2.filter(x => if (x(3).toString.equals("西城")) true else false)
    var count42: Accumulator[Int] = sc.accumulator(0)
    value42.foreach(x => {
      count42 += x(6).toInt
    })

    def max4(x: Int, y: Int): Unit = {
      if (x > y)
        println(s"4.朝阳比西城人多出${x - y}")
      else
        println(s"4.西城比朝阳人多出${y - x}")
    }

    max4(count41.value, count42.value)

    //    5、计算出标题中含有胡同的所有租户有多少人
    val value5 = rdd2.filter(x => if (x(1).toString.contains("胡同")) true else false)
    var count5: Accumulator[Int] = sc.accumulator(0)
    value5.foreach(x => {
      count5 += x(6).toInt
    })
    println("5.标题中含有胡同的人数为" + count5.value)
    //    6、计算出地铁1号线的租户有多少人？
    val value6 = rdd2.filter(x => if (x(5).toString.contains("1号")) true else false)
    var count6: Accumulator[Int] = sc.accumulator(0)
    value6.foreach(x => {
      count6 += x(6).toInt
    })
    println("6.地铁1号线的人数为" + count6.value)
    //    7、计算出东城区王府井站有多少人？
    val value7 = rdd2.filter(x => if (x(3).toString.equals("东城") && x(5).toString.contains("王府井站")) true else false)
    var count7: Accumulator[Int] = sc.accumulator(0)
    value7.foreach(x => {
      count7 += x(6).toInt
    })
    println("7.东城区王府井站的人数为" + count7.value)
    //    8、计算出所有地区的人数有多少？
    var count8: Accumulator[Int] = sc.accumulator(0)
    rdd2.foreach(x => {
      count8 += x(6).toInt
    })
    println("8.所有地区的人数为" + count8.value)

    //    9、计算出各个地区的平均人数是多少？

    var count9: Accumulator[Int] = sc.accumulator(0)
    //求出每个地区的人数
    val rdd9: RDD[(String, Iterable[Int])] = rdd2.map(x => (x(3), x(6).toInt)).groupByKey()
    rdd9.foreach(x => {
      count9 += x._2.sum
    })
    println("9.各个地区的平均人数是: " + count9.toString().toInt / rdd9.count())
    //    10、计算出地铁15号线比地铁1号线的人数多多少？
    var count101: Accumulator[Int] = sc.accumulator(0)
    var count102: Accumulator[Int] = sc.accumulator(0)
    rdd2.foreach(x => {
      if (x(5).startsWith("近地铁15号")) {
        count101 += x(6).toInt
      } else if (x(5).startsWith("近地铁1号")) {
        count102 += x(6).toInt
      }
    })
    println(s"10. 15号线比1号线多: ${count101.toString().toInt - count102.toString().toInt}")

    // 11、计算出人数最多的前三个地区
    val rdd11: RDD[(String, Int)] = rdd2.map(x => (x(3), x(6).toInt)).reduceByKey(_ + _)
    val resultSort: RDD[(String, Int)] = rdd11.sortBy(_._2, false)
    val finalResultSort: Array[(String, Int)] = resultSort.take(3)
    println("11.计算出人数最多的前三个地区")
    finalResultSort.foreach(println)
    //    12、计算出13号线的哪个站下的人最多？
    val rdd12 = rdd2.filter(x => if (x(5).toString.contains("13号")) true else false)
    val value12: RDD[(String, Int)] = rdd12.map(x => (x(5), x(6).toInt)).reduceByKey(_ + _)
    val resultSort12: RDD[(String, Int)] = value12.sortBy(_._2, false)
    val finalResultSort12: Array[(String, Int)] = resultSort12.take(1)
    println("12. 13号线的人数最多的站" + finalResultSort12(0))

    //    13、计算出望京地点14号线和15号线的下车总人数是多少？
    val rdd13 = rdd2.filter(x => if (x(4).toString.contains("望京")&&(x(5).toString.contains("14号")||x(5).toString.contains("15号"))) true else false)
    var count13: Accumulator[Int] = sc.accumulator(0)
    rdd13.foreach(x=>{
      count13+=x(6).toInt
    })
    println(" 13、计算出望京地点14号线和15号线的下车总人数为"+count13.value)


    //    14、计算出望京地点14号线占所有14号地铁站的人数比？
    val rdd141 = rdd2.filter(x => if ((x(5).toString.contains("14号"))) true else false)
    var count141: Accumulator[Int] = sc.accumulator(0)
    rdd141.foreach(x=>{
      count141+=x(6).toInt
    })
    val rdd142 = rdd2.filter(x => if (x(4).toString.contains("望京")&&(x(5).toString.contains("14号"))) true else false)
    var count142: Accumulator[Int] = sc.accumulator(0)
    rdd142.foreach(x=>{
      count142+=x(6).toInt
    })
    val d: Double = count142.value.toDouble / count141.value.toDouble
    val str: String = d.formatted("%.2f")
    println("14. 望京地点14号线占所有14号地铁站的人数比是"+str )

    //    15、计算出亚运村地点的人数和广渠门的人数总和？
    val rdd15 = rdd2.filter(x => if (x(4).toString.contains("亚运村")||(x(4).toString.contains("广渠门"))) true else false)
    var count15: Accumulator[Int] = sc.accumulator(0)
    rdd15.foreach(x=>{
      count15+=x(6).toInt
    })
    println("15.亚运村地点的人数和广渠门的人数总和为"+count15.value)
    //依次是：数据id号 	标题 	链接	地区	地点	地铁站	人数	日期
    //    16、计算出每个地区的地点人数最多的前3名？
    val rdd16: RDD[(String, Int)] = rdd2.map(x => ((x(3),x(6).toInt)))
    val groups: RDD[(String, Iterable[Int])] = rdd16.groupByKey()

    val groupedTop3: RDD[(String, List[Int])] = groups.map(grouped => {
      (grouped._1, grouped._2.toList.sortWith(_ > _).take(3))
    })
    println("16、计算出每个地区的地点人数最多的前3名")
    groupedTop3.collect().foreach(x=>{
      println(x._1+":")
      x._2.foreach { println }
    })

    sc.stop()
  }
}

