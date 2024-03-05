package chapter04

object Test02_ForLoop {
  def main(args: Array[String]): Unit = {
    // scala设计哲学：极简
    // for(i <- 1 to 9){  // to是方法调用  1.to(9)  1<=i<=9
    // for (i <- Range(1, 9)) { // 包前不包后  1<=i<9
    for (i <- 1 until 9) { // 自然语言习惯  1<=i<9
      for (j <- 1 to i) print(i + "*" + j + "=" + (i * j) + "     ")
      println()
    }

    // 并列循环 (改造嵌套循环)
    for (i <- 1 to 9; j <- 1 to i) {
      print(i + "*" + j + "=" + (i * j) + "     ")
      if (i == j) println()
    }

    // 引入变量  (定义j j与i有关 提上来的)
    for (i <- 1 to 9;
         j = 4 - i) {
      print(i + "*" + j + "=" + (i * j) + "     ")
      if (i == j) println()
    }

    // 集合遍历
    for (i <- Array(12, 134, 53)) println(i)
    for (i <- List(12, 134, 53)) println(i)
    for (i <- Set(12, 134, 53)) println(i)

    // 循环返回值 默认为空 (因为要执行多次)
    val a: Unit = for (i <- 1 to 10) {
      println(i)
    }
    println(a)
    // 多次执行 最终结果是集合
    val b= for (i <- 1 to 10) yield i*2
    println(b)
  }
}
