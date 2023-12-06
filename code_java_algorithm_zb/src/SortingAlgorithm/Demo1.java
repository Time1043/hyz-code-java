package SortingAlgorithm;

import static SortingAlgorithm.ArrayTool.*;

public class Demo1 {
    // 快速排序的主函数
    public static void quickSort(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;
        // 哨兵划分
        int pivot = partition(arr, left, right);
        // 递归左子数组 右子数组
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    // 哨兵划分：将一个较长数组的排序问题简化为两个较短数组的排序问题 (对数组进行分区的函数)
    private static int partition(int[] arr, int left, int right) {
        int i = left, j = right;  // 以arr[left]为基准数
        while (i < j) {
            while (i < j && arr[j] >= arr[left])
                j--;  // 从右到左找首个小于基准数的元素
            while (i < j && arr[i] <= arr[left])
                i++;  // 从左到右找首个大于基准数的元素
            swap(arr, i, j);
        }
        swap(arr, i, left);  // 将基准数交换至两个子数组的分界线
        return i;  // 返回基准数的索引
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("排序后的数组:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

