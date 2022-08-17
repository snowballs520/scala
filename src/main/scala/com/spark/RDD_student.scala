package com.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 1.一共有多少人参加考试？
 * 2.一共有多少个小于20岁的人参加考试？
 * 3.一共有多少个等于20岁的人参加考试？
 * 4.一共有多少个大于20岁的人参加考试？
 */
object RDD_student {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd_student")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("D:\\message/student.txt")

    val rdd1 = rdd.flatMap(_.split("\n"))
    val l = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(2).toInt)
    }).groupByKey().count().toInt


    val rdd3 = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(2).toInt)
    })
    val map = rdd3.collectAsMap()

    val map1 = map.filter(x => x._2 < 20)

    /**
     * 二.一共有多个男生参加考试？
     * 2.1 一共有多少个女生参加考试？
     */
    val rdd2 = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(3))
    })
    val map2 = rdd2.collectAsMap()

    /* 三.12班有多少人参加考试？
          13班有多少人参加考试？ */
    val l1 = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(0).toInt)
    }).filter(x => x._2 == 12).distinct().count() // 12班有多少人参加考试？

    val l2 = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(0).toInt)
    }).filter(x => x._2 == 13).distinct().count() //13班有多少人参加考试？

    /**
     * 四.语文科目的平均成绩是多少？
     * 4.1 数学科目的平均成绩是多少？
     * 4.2 英语科目的平均成绩是多少？
     */

    val rdd4 = rdd1.map(x => {
      (x.split(" ")(4), x.split(" ")(5).toInt)
    })
    val rdd41 = rdd4.combineByKey(
      v => (v, 1),
      (c: (Int, Int), v) => (c._1 + v, c._2 + 1),
      (c1: (Int, Int), c2: (Int, Int)) => (c1._1 + c2._1, c1._2 + c2._2))

    val ints_chinese = rdd41.filter(x => x._1 == "chinese").map(x => x._2._1 / x._2._2)

    val ints_math = rdd41.filter(x => x._1 == "math").map(x => x._2._1 / x._2._2)

    val ints_english = rdd41.filter(x => x._1 == "english").map(x => x._2._1 / x._2._2)

    /**
     * 五.单个人平均成绩是多少？
     */
    val rdd5 = rdd1.map(x => {
      (x.split(" ")(1), x.split(" ")(5).toInt)
    })
    val rdd51 = rdd5.combineByKey(
      v => (v, 1),
      (c: (Int, Int), v) => (c._1 + v, c._2 + 1),
      (c1: (Int, Int), c2: (Int, Int)) => (c1._1 + c2._1, c1._2 + c2._2))

    val student_avg_zds = rdd51.filter(x => x._1 == "张大三").map(x => x._2._1 / x._2._2)

    val student_avg_lds = rdd51.filter(x => x._1 == "李大四").map(x => x._2._1 / x._2._2)

    val student_avg_wf = rdd51.filter(x => x._1 == "王芳").map(x => x._2._1 / x._2._2)

    val student_avg_zs = rdd51.filter(x => x._1 == "张三").map(x => x._2._1 / x._2._2)

    val student_avg_wxf = rdd51.filter(x => x._1 == "王小芳").map(x => x._2._1 / x._2._2)

    val student_avg_ls = rdd51.filter(x => x._1 == "李四").map(x => x._2._1 / x._2._2)

    /**
     * 六.12班平均成绩是多少？
     * 6.1 12班男生平均总成绩是多少？
     * 6.2 12班女生平均总成绩是多少？
     * 6.3 同理求13班相关成绩
     */
    val rdd6 = rdd1.map(x => {
      (x.split(" ")(0), x.split(" ")(5).toInt)
    }).reduceByKey(_ + _).map(x => x._1 + "班平均成绩为:" + (x._2 / l1)) //12班平均成绩是多少？


    //  12班男生平均总成绩是多少
    val rdd61 = rdd1.map(x => {
      (x.split(" ")(0).toInt, x.split(" ")(1), x.split(" ")(3), x.split(" ")(5).toInt)
    }).filter(x => x._1 == 12 && x._3 == "男")

    //  12班女生平均总成绩是多少
    val rdd62 = rdd1.map(x => {
      (x.split(" ")(0).toInt, x.split(" ")(1), x.split(" ")(3), x.split(" ")(5).toInt)
    }).filter(x => x._1 == 12 && x._3 == "女")

    //  13班男生平均总成绩是多少
    val rdd63 = rdd1.map(x => {
      (x.split(" ")(0).toInt, x.split(" ")(1), x.split(" ")(3), x.split(" ")(5).toInt)
    }).filter(x => x._1 == 13 && x._3 == "男")

    //  13班女生平均总成绩是多少
    val rdd64 = rdd1.map(x => {
      (x.split(" ")(0).toInt, x.split(" ")(1), x.split(" ")(3), x.split(" ")(5).toInt)
    }).filter(x => x._1 == 13 && x._3 == "女")


    /**
     * 七.全校语文成绩最高分是多少？
     * 7.1 12班语文成绩最低分是多少？
     * 7.2 13班数学最高成绩是多少？
     */
    val arr = rdd1.map(x => {
      (x.split(" ")(4), x.split(" ")(5).toInt)
    }).filter(x => x._1 == "chinese").sortBy(x => x._2, false).first()


    val tuple = rdd1.map(x => {
      (x.split(" ")(4), x.split(" ")(5).toInt, x.split(" ")(0).toInt)
    }).filter(x => x._3 == 12 || x._1 == "chinese").sortBy(x => x._2).first()

    val tuple1 = rdd1.map(x => {
      (x.split(" ")(4), x.split(" ")(5).toInt, x.split(" ")(0).toInt)
    }).filter(x => x._3 == 13 || x._1 == "math").sortBy(x => x._2, false).first()

    /**
     * 八.总成绩大于150分的12班的女生有几个？
     */
    val l3 = rdd1.map(x => {
      (x.split(" ")(0).toInt, x.split(" ")(1), x.split(" ")(3), x.split(" ")(5).toInt)
    }).filter(x => x._3 == "女").map(x => (x._2, x._4)).groupByKey().mapValues(_.sum).filter(_._2 > 150).count()
    println("总成绩大于150分的12班的女生有"+l3+"个")

    /**
     * println("一共有"+rdd3.groupByKey().count()+"人参加考试")
     * println("一共有"+map1.count(x => true)+"个<20岁的人参加考试")
     * println("一共有"+map.filter(x => x._2 == 20).count(x => true)+"个等于20岁的人参加考试")
     * println("一共有"+map.filter(x=>x._2>20).count(x => true)+"个大于20岁的人参加考试")
     * println(map2.filter(x => x._2 == "男").count(x => true)) //一共有多个男生参加考试？
     * println(map2.filter(x => x._2 == "女").count(x => true)) //一共有多少个女生参加考试？
     * println("12班有"+l+"人参加考试")
     * println("13班有"+l2+"人参加考试")
     * println("语文科目的平均成绩为:"+ints_chinese.collect().toBuffer)
     * println("数学科目的平均成绩为:"+ints_math.collect().toBuffer)
     * println("英语科目的平均成绩为:"+ints_english.collect().toBuffer)
     * println("张大三的平均成绩为:"+student_avg_zds.collect().toBuffer)
     * println("李大四的平均成绩为:"+student_avg_lds.collect().toBuffer)
     * println("王芳的平均成绩为:"+student_avg_wf.collect().toBuffer)
     * println("张三的平均成绩为:"+student_avg_zs.collect().toBuffer)
     * println("王小芳的平均成绩为:"+student_avg_wxf.collect().toBuffer)
     * println("李四的平均成绩为:"+student_avg_ls.collect().toBuffer)
     * println(rdd6.collect.toBuffer)   12 13班平均分数
     * println("12班的男生平均总成绩为" + rdd61.map(x => x._4).reduce(_ + _).toLong / rdd61.map(x => x._2).distinct().count() + "分")
     * println("12班的女生平均总成绩为" + rdd62.map(x => x._4).reduce(_ + _).toLong / rdd62.map(x => x._2).distinct().count() + "分")
     * println("13班的男生平均总成绩为" + rdd63.map(x => x._4).reduce(_ + _).toLong / rdd63.map(x => x._2).distinct().count() + "分")
     * println("13班的女生平均总成绩为" + rdd64.map(x => x._4).reduce(_ + _).toLong / rdd64.map(x => x._2).distinct().count() + "分")
     * println("全校语文的最高成绩为" + arr._2 + "分")
     * println("12班语文成绩最低分是"+tuple._2+"分")
     * println("13班数学成绩最低分是"+tuple1._2+"分")
     **/
  }
}
