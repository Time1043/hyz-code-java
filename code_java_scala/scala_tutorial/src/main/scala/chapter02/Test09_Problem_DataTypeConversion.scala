package chapter02

object Test09_Problem_DataTypeConversion {
  def main(args: Array[String]): Unit = {
    val n: Int = 128
    val b: Byte = n.toByte // 强转 精度缺失
    println(b) // -128

    /*
    128 Int：
    原码：0000 0000 0000 0000 0000 0000 1000 0000
    补码：0000 0000 0000 0000 0000 0000 1000 0000

    强转 Byte：
    补码：1000 0000
    符号位1为负
    剩余位取反加1：111 1111 + 1 -> 1000 0000
    即-128
     */
  }
}
