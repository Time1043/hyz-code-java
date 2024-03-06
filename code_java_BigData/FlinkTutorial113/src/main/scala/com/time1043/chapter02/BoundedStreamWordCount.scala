package com.time1043.chapter02

import org.apache.flink.streaming.api.scala._

object BoundedStreamWordCount {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val lineDataStream: DataStream[String] = env.readTextFile("input/words.txt")

    // 对数据进行处理
    val wordAndOne = lineDataStream.flatMap(_.split(" ")).map(word => (word, 1))
    val wordAndOneGroup = wordAndOne.keyBy(data => data._1) // 返回当前二元组的第一个元素
    val sum = wordAndOneGroup.sum(1)
    sum.print()
    env.execute() // 执行
  }
}

/*
1> (scala,1)  分布式 多线程模拟
13> (world,1)
7> (hello,1)
4> (java,1)
19> (flink,1)
7> (hello,2)  来一个处理一次
7> (hello,3)
7> (hello,4)
*/