package SortingAlgorithmV1.DivideAndConquer;

import static SortingAlgorithmV1.ArrayTool.*;

public class MergeSort3ReversePairs {
    public static int reversePairs(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return 0;

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        // 终止条件
        if (left >= right)
            return 0;
        // left < right
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, res = 0;

        while (i <= mid && j <= right) {
            res += arr[i] > arr[j] ? (mid - i + 1) : 0;
            tmp[k++] = arr[i] > arr[j] ? arr[j++] : arr[i++];
        }
        while (i <= mid)
            tmp[k++] = arr[i++];
        while (j <= right)
            tmp[k++] = arr[j++];

        for (int l = 0; l < tmp.length; l++) {
            arr[left + l] = tmp[l];
        }
        return res;
    }

    private static int merge2(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = mid, j = right, k = tmp.length - 1, res = 0;

        while (i >= left && j >= mid + 1) {
            res += arr[i] > arr[j] ? (j - mid) : 0;
            tmp[k--] = arr[i] > arr[j] ? arr[i--] : arr[j--];
        }
        while (i >= left)
            tmp[k--] = arr[i--];
        while (j >= mid + 1)
            tmp[k--] = arr[j--];

        for (int l = 0; l < tmp.length; l++) {
            arr[left + l] = tmp[l];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("-------------测试开始-------------");
        int maxLen = 300;
        int maxValue = 100;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);

            if (reversePairs(arr1) != violence(arr2)) {
                System.out.println("-------------打印错误-------------");
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "正确！" : "错误！");
        System.out.println("-------------执行完毕-------------");
    }

    // 校验：暴力
    public static int violence(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return 0;

        int res = 0;
        for (int i = 0; i < arr.length; i++) {  // 遍历数组中所有的数字arr[i]
            for (int j = i; j < arr.length; j++) {  // 当前数的右边数字arr[j]
                res += arr[i] > arr[j] ? 1 : 0;
            }
        }
        return res;
    }
}
