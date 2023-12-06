package SortingAlgorithm;

public class ArrayTool {
    // 数据：随机生成数组
    public static int[] lenRandomValueRandom(int maxLex, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLex + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // 对数器/校验方法一：判断有序与否
    public static boolean isSorted(int[] arr) {
        // 边界处理：不到两个数默认升序
        if (arr == null || arr.length < 2)
            return true;

        int maxValue = arr[0];  // 不妨设0位置为最大值
        for (int i = 0; i < arr.length; i++) {
            if (maxValue > arr[i])
                return false;  // 若0位置真是最大值 即后面出现数居然更小 不是升序
            maxValue = Math.max(maxValue, arr[i]);  // 更新最大值
        }
        return true;
    }

    // 对数器/检验方法二：暴力方法对比
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
            return false;
        if (arr1 == null && arr2 == null)
            return true;
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    // 打印数组
    public static void printArray(int[] arr) {
        if (arr == null)
            return;

        for (int i = 0; i < arr.length; i++) {
            System.out.print(i != arr.length - 1 ? arr[i] + ", " : arr[i]);
        }
        System.out.println();
    }

    // 交换行为
    public static void swap(int[] arr, int i, int j) {
        if (i == j)  // 快排的两指针碰撞
            return;  // 若ij是相同索引 与自己异或结果为0
        arr[i] = arr[i] ^ arr[j];  // a=a^b
        arr[j] = arr[i] ^ arr[j];  // b=a^b  b=a^b^b  b=a
        arr[i] = arr[i] ^ arr[j];  // a=a^b  a=a^a^b  a=b
    }

    public static void swap2(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 拷贝数组内容
    public static int[] copyArray(int[] arr) {
        if (arr == null)
            return null;

        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
