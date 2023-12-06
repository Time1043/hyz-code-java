package SortingAlgorithm;

import java.util.Arrays;

import static SortingAlgorithm.ArrayTool.*;

public class QuickSort {
    public static void quickSort(int[] arr) {
        // 边界处理：不到两个数默认升序
        if (arr == null || arr.length < 2)
            return;

        processQuickSort(arr, 0, arr.length - 1);
    }

    public static void processQuickSort(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;
        // 哨兵划分
        int pivot = partition(arr, left, right);
        //  递归左子数组 右子数组
        processQuickSort(arr, left, pivot - 1);
        processQuickSort(arr, pivot + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int i = left, j = right;  // 以arr[left]为哨兵基准
        while (i < j) {
            while (i < j && arr[j] >= arr[left])
                j--;  // 从右往左找到首个小于哨兵的数字
            while (i < j && arr[i] <= arr[left])
                i++;  // 从左往右找到首个大于哨兵的数字
            swap(arr, i, j);  // 都找到则交换
        }
        swap(arr, i, left);  // 将基准数交换至两子数组的分界线
        return i;  // 返回基准数索引
    }

    public static void main(String[] args) {
        System.out.println("-------------测试开始-------------");
        int maxLen = 300;
        int maxValue = 1000;
        int testTime = 100000;  // 大样本随机验证
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);

            quickSort(arr1);  // 核心
            violence(arr2);  // 暴力

            if (!isEqual(arr1, arr2)) {
                System.out.println("-------------打印错误-------------");
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;  // 只要有一个错误就停止
            }
        }
        System.out.println(succeed ? "正确！" : "错误！");
        System.out.println("-------------执行完毕-------------");
    }

    // 暴力
    public static void violence(int[] arr) {
        Arrays.sort(arr);
    }
}
