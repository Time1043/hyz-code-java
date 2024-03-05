package SortingAlgorithmV1.DivideAndConquer;

import java.util.Arrays;

import static SortingAlgorithmV1.ArrayTool.*;

public class HeapSort {
    /*
    * 堆排序
    * 时间复杂度：O(NlogN)
    * 额外空间复杂度：O(1)
    * */

    // 堆排序
    public static void heapSort0(int[] arr) {
        // 建堆 堆化
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            siftDown0(arr, arr.length, i);
        }
        // 弹出堆顶元素 —— 循环n-1轮
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);  // 交换堆顶和堆底
            siftDown0(arr, i, 0);  // 从顶至底的堆化
        }
    }

    public static void siftDown0(int[] arr, int n, int i) {
        /* 堆的长度为n 从节点i开始 从顶至底堆化 */
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, ma = i;  // 节点i left right中值最大的记ma
            if (left < n && arr[left] > arr[ma])
                ma = left;
            if (right < n && arr[right] > arr[ma])
                ma = right;
            if (ma == i)  // i为三者最大 即前两if不更新 则结束
                break;
            swap(arr, i, ma);  // i和最大值交换
            i = ma;  // 迭代
        }
    }

    // 堆排序：每个元素依次入堆(完全有序)、最大值出堆
    public static void heapSort1(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return;

        // 每一新元素入堆(完全有序) —— O(NlogN）
        for (int i = 0; i < arr.length; i++) {  // O(N)
            heapInsert(arr, i);  // O(logN)
            // 每新增的一个元素 向上调整堆结构
        }

        // 最大值出堆 —— O(NlogN)
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {  // O(N) - 排序
            heapify(arr, 0, heapSize);  // O(logN)
            swap(arr, 0, --heapSize);  // O(1)
        }
    }

    // 堆排序：把数组变成大根堆(不用完全有序)、最大值出堆
    public static void heapSort2(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2)
            return;

        // 把数组变成大根堆(完全二叉树堆化) —— O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
            // 自堆底到堆顶 判断每个节点(向下)是否是大根堆
        }

        // 最大值出堆 —— O(NlogN)
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {  // O(N) - 排序
            heapify(arr, 0, heapSize);  // O(logN)
            swap(arr, 0, --heapSize);  // O(1)
        }
    }

    public static void heapInsert(int[] arr, int index) {
        /* 堆底乱时 往上调整堆 */
        // 停止条件：index不必父节点大、或index已经来到顶部
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;  // 迭代
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        /* 堆顶乱时 往下调整堆 */
        int left = 2 * index + 1;
        while (left < heapSize) {  // 若存在左子  (右子可能不存在)
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;  // 若右子存在且大于左子
            largest = arr[largest] > arr[index] ? largest : index;  // max{左,右} 和 {中} 比较
            if (largest == index)
                break;  // 若{中}大 即不变 则停止
            swap(arr, largest, index);  // 若max{左,右}大 则交换

            // 迭代
            index = largest;
            left = 2 * index + 1;
        }
    }


    // 对数器
    public static void testFramework() {
        System.out.println("-------------测试开始-------------");
        int maxLen = 300;
        int maxValue = 1000;
        int testTime = 100000;  // 大样本随机验证
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);

            heapSort1(arr1);  // 核心
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

    public static void main(String[] args) {
        testFramework();
    }

    // 暴力
    public static void violence(int[] arr) {
        Arrays.sort(arr);
    }
}
