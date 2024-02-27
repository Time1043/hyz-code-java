package chapter02

import chapter01.Student

object Test07_DataType {
  def main(args: Array[String]): Unit = {
    // 1 整数类型
    val a1: Byte = 127
    // val a2: Byte = 128  // error
    val a3 = 12 // 默认Int
    val a4 = 478023759023750923L // L

    val b1: Byte = 10
    // val b2: Byte = (10 + 20)  // 可以  10+20直接运算结果可以判断在Byte范围内
    // val b3: Byte = (b1 + 20)  // error  要运行时
    val b3: Byte = (b1 + 20).toByte // 强制类型转换

    // 2 浮点类型
    val f1 = 1.2345 // 默认Double
    val f2: Float = 3.14f //f

    // 3 字符类型
    val c1: Char = 'a'
    val c2: Char = '9'
    // 控制字符
    val c3: Char = '\t' // 制表符
    val c4: Char = '\n' // 换行符
    val c5 = '\\' // 转义
    val c6 = '\"' // 转义
    // 字符变量底层保存的是数ASCII
    val i1: Int = c1
    val i2: Int = c2
    println(i1) // 97
    println(i2) // 57

    // 4 布尔类型
    val isTrue: Boolean = true


    // 5 空类型：空值、空引用、什么都没有 (Unit Null Nothing)
    // 空值
    def m1(): Unit = {
      println("m1被调用了")
    }
    val a = m1() // Unit
    println(a) // ()
    // 空引用
    // val n: Int = null // error
    var s1 = new Student("zhangsan", 20)
    s1 = null // success

    // 什么都没有
    def m2(n: Int): Int = { // Nothing Int 公共父类
      if (n == 0)
        throw new NullPointerException
      else
        return n: Int
    }
    val b = m2(10) // 0 抛异常
    println(b)
  }
}
