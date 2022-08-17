package com.scala.function

object Demo1 {
  def main(args: Array[String]): Unit = {
    //    数组 array
    var arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    //    计算arr数组的和
    var sum = 0
    for (elem <- arr)
      sum += elem
    println(sum)
    println("------------------")
    arr.foreach(a => print(a + " "))
    println()
    arr.foreach(print(_))
    println()
    for (elem <- arr) {
      print(elem)
    }
    println
    println(arr.length)

    for (elem <- 0 until (arr.length)) {
      print(elem)
    }
    println
    for (elem <- arr if elem % 2 == 0) {
      print(elem + "\t")
    }
    //  吧数组添加守卫,(就是在遍历的时候加一个判断条件)
    for (ele <- 0 until (arr.length) if (arr(ele) % 2 == 0)) {
      print(arr(ele) + " ")
    }

    println()
    //  yield  将数组遍历后封装到新的数组中 (yield 后面跟的参数为for循环里面的变量)
    var arr2 = for (e <- arr if (e % 2 == 0)) yield e
    for (ele <- arr2) print(ele + " ")
    println()

    var arr3 = for (elem <- arr if elem % 3 == 0) yield elem
    for (elem <- arr3)
      print(elem + " ")


    //  99乘法表
    for (i <- 1 to 9; j <- 1 to i) {
      print(i + "*" + j + "=" + (i * j) + "\t")
      if (i == j) println
    }
    println()

    for (i <- 1 to 9; j <- 1 to i) {
      print(j + "*" + i + "=" + j * i + "\t")
      if (i == j) println()
    }


    //  冒泡排序
    println("=====================")

    var arr1 = Array(12, 2, 44, 1, 56, 14, 17, 8, 22, 10)
    for (i <- 0 to arr1.length - 1) {
      for (ele <- 0 until (arr1.length - 1 - i)) {
        var temp = 0
        if (arr1(ele) > arr1(ele + 1)) {
          temp = arr1(ele)
          arr1(ele) = arr1(ele + 1)
          arr1(ele + 1) = temp
        }
      }
    }
    for (ele <- arr1) print(ele + " ")




  }
}
