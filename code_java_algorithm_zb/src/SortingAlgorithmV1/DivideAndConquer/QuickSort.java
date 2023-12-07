package SortingAlgorithmV1.DivideAndConquer;

import java.util.Arrays;

import static SortingAlgorithmV1.ArrayTool.*;

public class QuickSort {

    /*
     * processQuickSort0：以arr[left]为基准，简单版本
     * processQuickSort1：以arr[right]为基准，partition1分两区，每次partition1后一个元素排序正确
     * processQuickSort2：以arr[right]为基准，partition1分三区，每次partition1后等区元素排序正确
     * processQuickSort3：
     *
     * processQuickSort1、processQuickSort2 最坏时间复杂度：O(N^2) —— 二叉树变单链表
     * 一次partition       =>  O(N)
     * 从右到左遍历，即需N次  =>  O(N*N)
     *
     *
     * */

    public static void quickSort(int[] arr) {
        // 边界处理：不到两个数默认升序
        if (arr == null || arr.length < 2)
            return;

        processQuickSort2(arr, 0, arr.length - 1);
    }

    public static void processQuickSort0(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;
        // 哨兵划分
        int pivot = partition0(arr, left, right);
        //  递归左子数组 右子数组
        processQuickSort0(arr, left, pivot - 1);
        processQuickSort0(arr, pivot + 1, right);
    }

    public static int partition0(int[] arr, int left, int right) {
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

    public static void processQuickSort1(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;

        // 以arr[right]为基准：小等区、大区、arr[right] -> swap(arr[right],小等区右一个)  ==>  arr[right]排序至正确位置
        int pivot = partition1(arr, left, right);
        // 左支递归
        processQuickSort1(arr, left, pivot - 1);
        // 右支递归
        processQuickSort1(arr, pivot + 1, right);
    }

    public static int partition1(int[] arr, int left, int right) {
        // 以arr[right]为基准：小等区、大区、arr[right] -> swap(arr[right],小等区右一个)  ==>  arr[right]排序至正确位置
        if (left > right)
            return -1;
        if (left == right)
            return left;

        int lessEqual = left - 1;  // 小等区右边界 0 -1
        int index = left;  // 遍历 1,2,3,4...

        while (index < right) {
            if (arr[index] <= arr[right])  // 以right为基准
                swap(arr, index, ++lessEqual);
            index++;
        }
        swap(arr, ++lessEqual, right);

        return lessEqual;
    }

    public static void processQuickSort2(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;

        // 以arr[right]为基准：小区、等区、大区  ==>  等区排序至正确位置
        int[] equalArea = partition2(arr, left, right);
        // 左支递归：等区-1 => 小区
        processQuickSort2(arr, left, equalArea[0] - 1);
        // 右支递归：等区+1 => 大区
        processQuickSort2(arr, equalArea[1] + 1, right);
    }

    public static int[] partition2(int[] arr, int left, int right) {
        // 特殊处理
        if (left > right)  // 不是有效边界
            return new int[]{-1, -1};
        if (left == right)  // 只有一个数
            return new int[]{left, right};

        int num = arr[right];  // 以arr[right]为基准
        int less = left - 1;  // 小区右边界 0 -1
        int more = right;  // 大区左边界 arr.len-1 +1  ——  right先停止在最后
        int index = left;  // 遍历 1,2,3,4...

        while (index < more) {  // 遍历指针和大区右边界碰上 则停止
            if (arr[index] == num)
                index++;                        // index走 => 等区右扩
            else if (arr[index] < num) {
                swap(arr, index++, ++less);     // 交换 小区右扩 index走
                /*swap(arr, index, left + 1); less++; index++;*/
            } else {  // arr[index] > num
                swap(arr, index, --more);       // 交换 大区左扩 index不走
            }
        }
        swap(arr, more, right);                 // 补全最后一步  ——  right和大区第一个交换

        return new int[]{less + 1, more};  // 返回等区左右边界
    }

    public static void processQuickSort3(int[] arr, int left, int right) {
        // 子数组长度为1 则停止递归
        if (left >= right)
            return;

        // 在left到right中随机选一个位置  和arr[right]交换
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);

        // 以arr[right]为基准：小区、等区、大区  ==>  等区排序至正确位置
        int[] equalArea = partition2(arr, left, right);
        // 左支递归：等区-1 => 小区
        processQuickSort3(arr, left, equalArea[0] - 1);
        // 右支递归：等区+1 => 大区
        processQuickSort3(arr, equalArea[1] + 1, right);
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
