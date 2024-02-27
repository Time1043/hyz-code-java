package chapter01

// 入口类  【.class]
class Student(name: String, var age: Int) { // 属性 构造方法
  def printInfo(): Unit = {
    /*println(this.name + "  " + this.age)*/
    println(name + "  " + age + "  " + Student.school)
  }
}

// 引入伴生对象 和Student类相伴相生 (名称必须一样 且放在同一个文件中)  【$.class】
object Student {
  val school: String = "jld"

  def main(args: Array[String]): Unit = {
    val s1 = new Student("张三", 20)
    val s2 = new Student("李四", 24)
    s1.printInfo()
    s2.printInfo()
  }
}