package SortingAlgorithm;

import java.util.Arrays;

import static SortingAlgorithm.ArrayTool.copyArray;
import static SortingAlgorithm.ArrayTool.printArray;

public class MergeSort {
    // 递归实现归并排序
    public static void mergeSort1(int[] arr, int left, int right) {
        // 边界处理：不到两个数默认升序
        if (arr == null || arr.length < 2)
            return;

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        // 终止条件：当子数组长度为1时终止递归  (一个数天然有序)
        if (left >= right)   // left == right
            return;
        // 划分阶段  (不止一个数)
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);  // 左边有序 递归左子数组
        process(arr, mid + 1, right);  // 右边有序 递归右子数组
        // 合并阶段
        merge(arr, left, mid, right);  // 左右有序
    }

    // 迭代实现归并排序
    public static void mergeSort2(int[] arr) {
        // 边界处理：不到两个数默认升序
        if (arr == null || arr.length < 2)
            return;

        int N = arr.length;
        int step = 1;  // 步长：1,2,4,8
        while (step < N) {
            int left = 0;  // 当前左组的第一个位置
            while (left < N) {
                if (step >= N - left)
                    break;
                int mid = left + step - 1;
                int right = mid + Math.min(step, N - mid - 1);
                merge(arr, left, mid, right);  // 关键merge
                left = right + 1;  // 迭代
            }
            if (step > N / 2)  // 防止溢出
                break;
            step <<= 1;  // 步长乘2
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组tmp 用于存放合并后的结果  【额外空间复杂度】
        int[] tmp = new int[right - left + 1];
        // 初始化左子数组和右子数组的起始索引
        int i = left, j = mid + 1, k = 0;  // 指针ij记录左右越界  k为tmp数组

        // 当左右子数组都还有元素时：比较并将较小的元素复制到临时数组中
        while (i <= mid && j <= right)
            tmp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];  // 谁小拷贝谁
        // 当一个子数组没有元素：将另一个子数组剩余元素复制到临时数组中
        while (i <= mid)   // 左子数组剩余
            tmp[k++] = arr[i++];
        while (j <= right)   // 有子数组剩余
            tmp[k++] = arr[j++];

        // 将临时数组tmp中的元素复制回数组arr的对应区间
        for (int l = 0; l < tmp.length; l++) {
            arr[left + l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        System.out.println("-------------测试开始-------------");
        int maxLen = 300;
        int maxValue = 1000;
        int testTime = 100000;  // 大样本随机验证
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Comparator.lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);

            /*mergeSort2(arr1);*/
            mergeSort1(arr1, 0, arr1.length - 1);  // 核心
            Comparator.violence(arr2);  // 暴力

            if (!Comparator.isEqual(arr1, arr2)) {
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
}

class Comparator {
    // 对数器/样本：随机生成数组 (长度随机 值随机)
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        // 数组：maxLen
        int[] ans = new int[(int) (Math.random() * maxLen + 1)];  // [0,1) -> [0,maxLen) -> [1,maxLen+1)
        // 填值：maxValue
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

    // 暴力
    public static void violence(int[] arr) {
        Arrays.sort(arr);
    }
}
