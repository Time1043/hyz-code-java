package SortingAlgorithmV2.DivideAndConquer;

import java.util.Arrays;

import static SortingAlgorithmV2.ArrayComparatorTool.*;

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
        testFramework((arr1, arr2) -> {
            quickSort(arr1);
            violenceSort(arr2);
            return isEqual(arr1, arr2);
        });
    }
}
