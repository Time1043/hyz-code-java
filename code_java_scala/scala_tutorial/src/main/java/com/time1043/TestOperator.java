package com.time1043;

public class TestOperator {
    public static void main(String[] args) {
        // 2 关系运算符 比较运算符  (最终结果是t f)
        // 值类型比较内容  引用类型比较内存地址
        String s1 = "hello";
        String s2 = new String("hello");
        Boolean isEqual = s1 == s2;
        System.out.println(isEqual);  // f
        System.out.println(s1.equals(s2));  // t

        // 4 赋值运算符
        byte b = 10;
        b = (byte) (b + 1); // byte <- int
        b += 1; // 存在强转
        System.out.println(b);
        // 自增自减  左++ 右++
        int x = 15;
        int y = x++;
        System.out.println("x=" + x + ",y=" + y); // x=16,y=15
        int z = ++x;
        System.out.println("x=" + x + ",z=" + z); // x=17,z=17
        x = 23;
        x = x++;  // temp = x++;  x=temp
        System.out.println(x);  // 23
    }
}
