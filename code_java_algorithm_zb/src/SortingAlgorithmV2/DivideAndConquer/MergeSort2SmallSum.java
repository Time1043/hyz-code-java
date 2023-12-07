package SortingAlgorithmV2.DivideAndConquer;

import static SortingAlgorithmV2.ArrayComparatorTool.*;

public class MergeSort2SmallSum {
    public static int smallSum(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return 0;

        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
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
            res += arr[i] < arr[j] ? (right - j + 1) * arr[i] : 0;
            tmp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
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

    // 校验：暴力
    public static int violence(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return 0;

        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        testFramework((arr1, arr2) -> smallSum(arr1) == violence(arr2));  // 比较两个返回值是否相同
    }


}
