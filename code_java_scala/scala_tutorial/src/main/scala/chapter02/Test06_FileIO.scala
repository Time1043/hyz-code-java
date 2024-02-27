package chapter02

import java.io.{File, PrintWriter}
import scala.io.Source

object Test06_FileIO {
  def main(args: Array[String]): Unit = {
    // 从文件中读取数据
    Source.fromFile("src/main/resources/test.txt").foreach(print) // 一行数据 foreach 函数式编程

    // 将数据写入文件 (scala没有实现 java有)
    val writer = new PrintWriter(new File("src/main/resources/output.txt"))
    writer.write("hello scala from java writer")
    writer.close()

  }
}
