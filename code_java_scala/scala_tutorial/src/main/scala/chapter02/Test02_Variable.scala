package chapter02

import chapter01.Student

object Test02_Variable {
  def main(args: Array[String]): Unit = {
    // 值类型
    var a1: Int = 10 // 声明变量
    var a2 = 20 // 类型可省略 自动类型推导 Int
    val b1 = 23 // 常量 【更推荐】

    // a2 = "haha"  // 不允许  类型确定后不能更改 强类型语言
    // var a3  // 不允许  变量声明后要有初始值


    // 引用类型
    val s1 = new Student("张三", 29)
    // s1 = new Student("张三丰", 30)  // 常量不允许修改 对象
    s1.age = 24 // 对象的属性能够修改  (集合对象整个不能更改 里面的内容可以更改)
    s1.printInfo()
    var s2 = new Student("李四", 24)
    s2 = null // 变量是可以修改的

  }
}
