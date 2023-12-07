package SortingAlgorithmV1.ThreeSort;

import static SortingAlgorithmV1.ArrayTool.printArray;

public class ThreeSort2ShellSort {
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int N = arr.length;
        // 初始增量设为数组长度的一半
        for (int gap = N / 2; gap > 0; gap /= 2) {
            // 对每个子序列应用直接插入排序
            for (int i = gap; i < N; i++) {
                int base = arr[i];
                int j = i - gap;
                // 类似直接插入排序的内循环，但步长为gap
                while (j >= 0 && arr[j] > base) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = base;
            }
            printArray(arr);
        }
    }

    public static void main(String[] args) {
        // 测试数组
        int[] testArray = {8, 5, 2, 6, 9, 3, 1, 4, 0, 7};

        // 打印原始数组
        System.out.println("Original Array:");
        printArray(testArray);

        // 进行希尔排序
        shellSort(testArray);

        // 打印排序后的数组
        System.out.println("Sorted Array:");
        printArray(testArray);
    }

}
