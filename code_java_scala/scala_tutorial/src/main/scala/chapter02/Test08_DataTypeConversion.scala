package chapter02

object Test08_DataTypeConversion {
  def main(args: Array[String]): Unit = {
    // 自动类型转换
    val a1: Byte = 10
    val b1: Long = 2234 // 自动类型转换  val b1: Long = 2234L
    println(a1 + b1) // 自动类型转换 以更高精度为准
    println((a1 + b1).toInt) // 强制类型转换

    val a2: Byte = 10
    val b2: Int = a2 // 自动转换
    // val c2: Byte = b2  // error
    val c2: Byte = b2.toByte // 强制类型转换

    // (byte short) 和 char 不会相互转换
    val a3: Byte = 10
    // val b3: Char = a3  // error
    val b3: Char = 'b'
    // val c3: Byte = b3  // error
    val c3: Byte = b3.toByte // 强制类型转换

    // 三者转换需要借助Int
    val a4: Byte = 12
    val b4: Short = 25
    val c4: Char = 'c'
    val result4: Int = a4 + b4 + c4
    println(result4)


    // 强制类型转换
    val n1: Int = -2.5.toInt
    println(n1) // 丢掉小数部分
    val n2: Int = (2.6 + 3.7).toInt // 最近操作对象
    println(n2)

    // 数值 1231 -> String "1231"
    val n3: Int = 27
    val s3: String = n3 + ""
    println(s3)
    // String "1231" -> 数值 1231
    val n4: Int = "12".toInt // 强制类型转换
    val n5: Float = "3.12".toFloat // 强制类型转换
    // val n6: Int = "3.12".toInt // error
    val n6: Int = "3.12".toDouble.toInt
  }
}
