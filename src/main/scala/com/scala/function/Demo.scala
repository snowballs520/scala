package com.scala.function

object Demo {
  def main(args: Array[String]): Unit = {
  /**    var name1 = "张三"
     var a = 10
     print(name ,a)
     var stu = new student(12, "jack")
     val id = stu.id
     println("id:  " + id)
     println("name:  " + stu.name)

     //  scala 中使用+=, -= 代替java中的++,--
     var i = 10
     i -= 1
     print(i)*/

//    if else  scala 条件语句
    /* var a = 10

    val i = if (a % 3 == 0) 1else 2

    println(i)*/

//    scala循环语句
//        for循环   [1 to 10 表示从 1 到 10 , 包头包尾]
    for(a<-1 to 10)
      println(a+"__")

//    until 循环  [1 until 10 表示 1 到 10 ,包括 1 , 但是不包括 10 ]
    for(a<-1 until 10)
      print(a+"__")

  /** println(if (a == 9) "正确" else if (a < 9) "错误")

   val estimate
   = if (a < 9)
     "正确"
   else if (a == 9)
     "太对了"
   print(estimate)*/
  }
}
