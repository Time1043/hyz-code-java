package chapter01

object HelloWorld { // object 关键字 声明一个单例对象 (伴生对象)
  def main(args: Array[String]): Unit = { // def 方法名称(参数名称: 参数类型): 返回值类型 = { 方法体 }
    println("hello world")
    System.out.println("hello scala from java")
  }

}