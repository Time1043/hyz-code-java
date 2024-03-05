package chapter03

object Test01_TestOperator {
  def main(args: Array[String]): Unit = {
    // 1 算术运算符
    val result1 = 10 / 3 // 整数除法 只保留整数部分
    println(result1)
    val result2: Double = 10 / 3 // 3.0
    println(result2)
    val result3: Double = 10.0 / 3 // 3.33333...
    println(result3.formatted("%5.2f"))
    val result4 = 10 % 3 // 取余运算 1
    println(result4)

    // 2 关系运算符 比较运算符  (最终结果是t f)
    // java：值类型比较内容  引用类型比较内存地址
    // scala：判断的是内容(常用)  判断的是引用地址(少用)
    val s1: String = "hello"
    val s2: String = new String("hello")
    println(s1 == s2) // t  即方法
    println(s1.equals(s2)) // t
    println(s1.eq(s2)) // f  判断的是引用地址(少用)

    // 3 逻辑运算符  多个条件 短路特性
    def m(n: Int): Int = {
      println("m被调用")
      return n
    }

    val n1 = 1
    println((4 > 5) && m(n1) > 0) // f 短路

    // 【应用】判断一个字符串是否为空
    def isNotEmpty(str: String): Boolean = {
      return (str != null) && !("".equals(str.trim))
    }

    println(isNotEmpty(null))

    // 4 赋值运算符
    // var b: Byte = 10  // byte <- int  报错
    var b: Int = 10
    b += 1
    println(b)

    // 5 位运算符 二进制 快
    val a1 = 60 // 0011 1100
    val b1 = 13 // 0000 1101
    println(a1 & b1) // 0000 1100  12
    println(a1 | b1) // 0011 1101  61
    println(~a1) // 1100 0011  -61 负数 取反加1
    // 异或 相同为0相异为1
    println(a1 << 2) // 240

    // scala中的运算符 本质上都是 对象的方法调用
    val num1: Int = 12
    val num2: Int = 37
    println(num1.+(num2)) // 对象的方法调用 彻底的面向对象
    println(num1 + (num2)) // 兼容过去的习惯
    println(7.5.toInt.toString)
    println(7.5 toInt) // 自然语言习惯
  }
}
