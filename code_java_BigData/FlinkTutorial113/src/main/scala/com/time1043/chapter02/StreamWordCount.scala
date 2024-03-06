package com.time1043.chapter02

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._

object StreamWordCount {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // val lineDataStream: DataStream[String] = env.socketTextStream("192.168.45.108", 7777) // 监听端口 socket
    // --host 192.168.45.108 --port 7777
    val parameterTool = ParameterTool.fromArgs(args)
    val hostname = parameterTool.get("host")
    val port = parameterTool.getInt("port")
    val lineDataStream: DataStream[String] = env.socketTextStream(hostname, port) // 不写死

    // 对数据进行处理
    val wordAndOne = lineDataStream.flatMap(_.split(" ")).map(word => (word, 1))
    val wordAndOneGroup = wordAndOne.keyBy(_._1)
    val sum = wordAndOneGroup.sum(1)
    sum.print()
    env.execute() // 执行
  }
}
