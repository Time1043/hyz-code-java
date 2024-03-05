package SortingAlgorithmV2;

import java.util.Arrays;

public class ArrayComparatorTool {
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

    // 暴力排序
    public static void violenceSort(int[] arr) {
        Arrays.sort(arr);
    }


    @FunctionalInterface
    public interface DifferentRule {
        boolean test(int[] arr1, int[] arr2);
    }

    // 普通对数器 (主方法 带参数)
    public static void testFramework(int maxLen, int maxValue, int testTime, DifferentRule differentRule, boolean printError) {
        System.out.println("-------------测试开始-------------");
        boolean succeed = true;
        // 记录起始时间  记录起始内存
        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);

            if (!differentRule.test(arr1, arr2)) {
                if (printError) {
                    System.out.println("-------------打印错误-------------");
                    printArray(arr1);
                    printArray(arr2);
                }
                succeed = false;
                break;
            }
        }

        // 记录结束时间 记录结束内存
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // 计算消耗时间 计算消耗内存
        long timeTaken = endTime - startTime;
        long memoryUsed = endMemory - startMemory;

        System.out.println(succeed ? "正确！" : "错误！");
        System.out.println("时间消耗：" + timeTaken + " ms");
        System.out.println("内存消耗：" + memoryUsed + " bytes");
        System.out.println("-------------执行完毕-------------");
    }

    // 普通对数器 (重载方法 默认参数)
    public static void testFramework(DifferentRule keyMethod) {
        testFramework(1000, 3000, 10000, keyMethod, true);  // 使用默认值 调用主方法
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
