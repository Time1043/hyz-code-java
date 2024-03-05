package chapter04

object Test04_Practice_Pyramid {
  def main(args: Array[String]): Unit = {
    // 趣味练习
    for (i <- 1 to 9; stars = 2 * i - 1; spaces = (17 - stars) / 2) {
      println(" " * spaces + "*" * stars)
    }

    for (stars <- 1 to 17 by 2; spaces = (17 - stars) / 2) {
      println(" " * spaces + "*" * stars)
    }
  }
}
