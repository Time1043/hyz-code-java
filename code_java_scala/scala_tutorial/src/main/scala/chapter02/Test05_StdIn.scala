package chapter02

import scala.io.StdIn

object Test05_StdIn {
  def main(args: Array[String]): Unit = {
    println("请输入用户名：")
    val name: String = StdIn.readLine() // 返回值是String
    println("请输入您的年龄：")
    val age: Int = StdIn.readInt() // 返回值是Int
    println(s"${age}的${name}，您好")
  }
}
