package com.scala.function

object funDemo {
  //  函数定义在方法外和方法内都可使用,定义在方法外所有方法都可使用, 在方法内的只有此方法可以使用
  var f1: (Int, Int) => Int = { (x, y) => x + y } //   第一种

  var f2 = (x: Int, y: Int) => x - y // 第二种


  def main(args: Array[String]): Unit = {
    /*   val 函数名 ：（函数数据类型） =》 返回值类型 = {（参数名）=> 执行体}
    定义函数的几种方法 */

    //    var a = 10
    //    var b = 20
    //    var c = f1(a, b)
    //    var d = f2(a, b)
    //    println(c)
    //    println(d)
    //    //   匿名函数
    //    var map = (x: String) => x + "*"
    //    val arr = Array("1", "2", "3")
    //    val str = arr.map(map)
    //    val str1 = arr.map(a=>a+"*")
    //    println(str.toBuffer)
    //    println(str1.toBuffer)


   /* def method2(f3: (Int, Int) => Int, x: Int, y: Int) = f3(x, y)

    def f3 = (x: Int, y: Int) => x * y

    val int = method2((x, y) => x + y, 30, 20)
    println(int) //50

    val i = method2(f3, 10, 20)
    println(i) //200
    var m: (Int, Int) => Int = method2 _
    var p: Int = m(10, 20)
    println(p)*/

    var strs = Array("hello zhangsan hello scala hello scala", "tom jack hello jack tom scala lucy")
    strs.flatMap(x=>x.split(" ")).groupBy(x=>x).mapValues(_.length).toList.sortBy(_._2).foreach(x=>println(x))

  }
}
