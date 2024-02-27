package com.time1043;

public class Student {
    private String name;
    private Integer age;
    private static String school = "jld";

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void printInfo() {
        System.out.println(this.name + "  " + this.age + "  " + Student.school);  // Student.school 类名访问 不是对象调用
    }

    public static void main(String[] args) {
        Student s1 = new Student("张三", 20);
        Student s2 = new Student("李四", 23);
        s1.printInfo();
        s2.printInfo();
    }
}
