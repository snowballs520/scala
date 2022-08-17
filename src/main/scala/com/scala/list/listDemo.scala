package com.scala.list

object listDemo {
  def main(args: Array[String]): Unit = {
    //    创建集合,使用拼接模式   最后一个元素后面必须再加上  ::Nil
    var list = 1 :: 2 :: 3 :: Nil
    //    普通创建集合的方法
    var list5 = List(9, 8, 7, 6)
    //    向集合前面追加数据 (+:) 返回一个新的集合;
    val list1 = list.+:(0)

    //    向集合后面添加元素,  (:+)  返回新的集合
    val list2 = list.:+(4)

    // 向集合头部追加集合  (++:)
    val list3 = list.++:(list5)

    //    向尾部追加集合   (++)
    val list4 = list ++ (list5)

    //拼接集合   (集合:::集合)
    val list6 = list ::: list5

    //    拼接元素  再集合后面添加
    val list7 = list :: List(11, 12)
    val list8 = list7.::(13)

    //    拼接元素,在集合前面进行添加
    val list9 = 20 :: list8
    //    统计集合内符合条件的所有元素的个数
    val i = list.count(x => x % 2 == 0)
    val list11 = List("hello", "scala", "hello", "tom")
    val tuples = list11.map((_, 1))
    val tuples1 = tuples.filter(x => x._1.length >= 5)
    println(tuples1.toBuffer)

  }
}
