package com.spark.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("netWorkWordCount").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(1))
    val rid = ssc.socketTextStream("hadoop6", 9999)
    val ds = rid.flatMap(x => x.split(" "))
    val ds1 = ds.map((_, 1))
    val ds2 = ds1.reduceByKey(_ + _)
    ds2.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
