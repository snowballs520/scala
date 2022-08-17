package com.scala.function

object methodDemo1 {

  var f1: (Int, Int) => Int = { (x, y) => x + y }

  def main(args: Array[String]): Unit = {
    val i = m2(10, 20)
    println(i)

    val a: Int = f1(10, 20)
    println(a)

  }
  def m2(x: Int, y: Int) = {
    "String"
  }


}
