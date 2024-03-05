package SortingAlgorithmV2.ThreeSort;

import static SortingAlgorithmV2.ArrayComparatorTool.*;

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
        }
    }

    public static void main(String[] args) {
        testFramework(
                ((arr1, arr2) -> {
                    shellSort(arr1);  // 更优解
                    violenceSort(arr2);  // 暴力解
                    return isEqual(arr1, arr2);  // 比较数组内容
                })
        );
    }
}
