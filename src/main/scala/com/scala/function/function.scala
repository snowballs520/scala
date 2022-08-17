package com.scala.function

object function {
  def main(args: Array[String]): Unit = {
    def a(x: Int, y: Int) = x * y

    def klh(a: Int) = (b: Int) => a * b

    // 偏函数
    println(demo("b"))
    println(pian("c"))

    println(score(80))
  }

  //     偏函数 partialFunction
  def demo(x: Any): Int = {
    val i = if (x.equals("a")) 97 else 0
    i
  }

  //  定义偏函数  偏函数用于匹配输入的类型
  def pian: PartialFunction[Any, Int] = {
    case "a" => 97
    case "b" => 98
    case _ => 10
  }

  //   自定义匹配分数
  def score: PartialFunction[Int, String] = {
    case 90 => "优秀"
    case 80 => "合格"
  }

}
