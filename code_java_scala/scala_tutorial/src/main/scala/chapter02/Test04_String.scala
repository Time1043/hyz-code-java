package chapter02

object Test04_String {
  def main(args: Array[String]): Unit = {
    val name = "zhangsan"
    val age = 18
    println(age + "岁的" + name + "向您问好") // 字符串拼接 +
    println(age + "岁的" + name * 3 + "向您问好") // 字符串拼接 *乘是多个相加的结果

    printf("%d岁的%s向您问好\n", age, name) // printf %占位符
    println(s"${age}岁的${name}向您问好") // 字符串模板

    val num: Double = 3.1415926535
    println(f"The num is ${num}%2.2f.") // 字符串模板 格式化
    println(raw"The num is ${num}%2.2f.") // 不解析 换行符也不解析

    // 三引号表示字符串 保持多行字符串的原格式输出 sql
    val sql =
      s"""
         |select *
         |from student
         |where name = ${name} and >= ${age}
         |""".stripMargin // 忽略边界
    println(sql)
  }
}
