package d001;

public class D009ListReverse1 {
    public static void main(String[] args) {
        // 基础数据类型  按值传递
        int a = 10;
        f(a);
        System.out.println(a);  // 10

        // 其他类型  按引用传递
        Number b = new Number(5);
        g1(b);
        System.out.println(b.val);  // 5
        g2(b);
        System.out.println(b.val);  // 6

        // 一维数组
        int[] c = {1, 2, 3, 4};
        g3(c);
        System.out.println(c[0]);  // 1
        g4(c);
        System.out.println(c[0]);  // 100
    }

    public static void f(int a) {
        a = 0;
    }

    public static class Number {
        public int val;

        public Number(int v) {
            val = v;
        }
    }

    public static void g1(Number b) {
        b = null;
    }

    public static void g2(Number b) {
        b.val = 6;
    }

    public static void g3(int[] c) {
        c = null;
    }

    public static void g4(int[] c) {
        c[0] = 100;
    }
}
