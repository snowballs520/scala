package com.scala.function

import scala.collection.mutable.ArrayBuffer

object methodDemo {
  def f3 = (x: Int, y: Int) => (x * y)

  def main(args: Array[String]): Unit = {
    //   定义 方法时直接写入函数,固定函数类别,根据所传入的运算符进行运算.
    val int = method2((x, y) => x + y, 30, 20)
    println(int)
    //    调用方法时直接调用函数,结果根据之前写的函数进行运算
    val i = method2(f3, 10, 20)
    println(i)
    println("==============")

    //  scala 数组
    // 数组的定义。
    val arr = Array(14, 21, 3, 7)
    // 获取数组中的值。
    println(arr(0))
    // 使用for循环遍历数组。
    println("遍历数组")
    for (ele <- 0 until arr.length) {
      println(arr(ele))
    }
    var f1 = (x: Int) => x * 10
  }

  def method(x: Array[Int]) = {
    var arr = ArrayBuffer[Int]()
    for (ele <- x) {
      arr += (ele * 10) //+= 向其内部追加。相当于append
    }
    arr.toArray
  }


  def method2(f3: (Int, Int) => Int, x: Int, y: Int) = f3(x, y)


  //   方法可转换为函数, 在方法后添加 ' _ '
  /**
   * def method1(x: Int, y: Int) = x + y
   * val f9: (Int, Int) => Int = method1 _
   * val res10: Int = f9(10, 7)
   * println(res10)
   */
  /*method1(10)
  println(method2(30))
  println(method3()) */


  /* def method1(a: Int): Unit = {
    println(a)
  }

  def method2(a: Int): Int = {
    20
  }

  def method3(): Int = {
    30
  }
*/


}
