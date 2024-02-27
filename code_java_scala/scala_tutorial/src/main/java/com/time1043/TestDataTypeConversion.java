package com.time1043;

public class TestDataTypeConversion {
    public static void main(String[] args) {
        byte b = 10;
        test(b);
    }

    /*public static void test(byte b) {  // 【1】
        System.out.println("bbb");
    }*/

    /*public static void test(short s) {  // 【2】 没byte 自动类型转换
        System.out.println("sss");
    }*/
    
    public static void test(int i) {  // 【3】 自动类型转换
        System.out.println("iii");
    }

    public static void test(char c) {  // 字符
        System.out.println("ccc");
    }
}
