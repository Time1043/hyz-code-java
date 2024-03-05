package chapter04

import scala.io.StdIn

object Test01_IfElse {
  def main(args: Array[String]): Unit = {
    println("请输入您的年龄：")
    val age: Int = StdIn.readInt()

    // 单分支
    if (age >= 18) {
      println("成年")
    }

    // 双分支
    if (age > 18) {
      println("成年成年")
    } else {
      println("未成年未成年")
    }

    // 多分支
    val result: Any = if (age <= 6) {
      "童年"
    } else if (age < 18) {
      "青少年"
    } else if (age < 40) {
      age
    } else {
      age
    }
    println("result=" + result)

    // java 三元运算符
    val res1: String = if (age >= 18) {
      "成年"
    } else {
      "未成年"
    }
    val res2: String = if (age >= 18) "成年" else "未成年" // 贴近自然语言

    // 嵌套分支 不推荐
  }
}
