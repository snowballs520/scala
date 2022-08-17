package com.scala.function

object functionDemo {
  //    创建函数
  var f1: (Int, Int) => Int = { (x, y) => x * y }
  //    简略写法   不写函数类型, 由 scala 代码自动推算;
  var f2 = (x: Int, y: Int) => x + y

  def main(args: Array[String]): Unit = {


    def method(x: Int*): Unit = {
      var sum = 0
      for (ele <- x) {
        sum += ele
      }
      println(sum)
    }

    def apply(uu: Int => String, v: Int) = uu(v)

    def layout(x: Int) = "[" + x.toString + "]"

    println("--------------")

    val i = m1(f1, 110, 20)
    println(i)

    println("===========")
    //    调用方法时,直接将函数作为参数写入
    val i1 = m1(_+_, 10, 20)
    println(i1)
    println("===========")
    val i2 = add(fAdd, 10, 20)
    println(i2)
    val i3 = add(fDelete, 10, 20)
    println(i3)
    val i4 = add(fMul, 10, 20)
    println(i4)

  }

  //  函数当做方法参数传递

  def m1(f3: (Int, Int) => Int, x: Int, y: Int): Int = {
    f3(x, y)
  }

  /** 创建函数
   * 加法  减法  乘法 */
  var fAdd = (x: Int, y: Int) => x + y // 加法
  var fDelete: (Int, Int) => Int = { (x, y) => x - y } //减法
  var fMul = (x: Int, y: Int) => x * y //乘法

  //        (函数体                 ,参数   ,参数    ):返回值类型 = 方法体
  def add(add: (Int, Int) => Int, x: Int, y: Int): Int = add(x, y)


}
