package com.time1043.chapter02

import org.apache.flink.api.scala._

object BatchWordCount {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment // 创建执行环境
    val lineDataSet: DataSet[String] = env.readTextFile("input/words.txt") // 读取数据

    // 对数据进行操作
    val wordAndOne = lineDataSet.flatMap(_.split(" ")).map(word => (word, 1))
    val wordAndOneGroup = wordAndOne.groupBy(0) // 索引0 分组
    val sum = wordAndOneGroup.sum(1) // 索引1 聚合
    sum.print()
  }
}

/*
(java,1)
(scala,1)
(hello,4)
(flink,1)
(world,1)
*/