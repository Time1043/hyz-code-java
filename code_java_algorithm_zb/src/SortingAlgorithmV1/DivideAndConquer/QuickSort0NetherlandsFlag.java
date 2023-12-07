package SortingAlgorithmV1.DivideAndConquer;

import static SortingAlgorithmV1.ArrayTool.*;

public class QuickSort0NetherlandsFlag {
    // 划分小等区、大区
    public static int partition1(int[] arr, int left, int right) {
        if (left > right)
            return -1;
        if (left == right)
            return left;

        int num = arr[right];  // 以arr[right]为基准
        int lessEqual = left - 1;  // 小等区右边界 0 -1
        int index = left;  // 遍历 1,2,3,4...

        while (index < right) {
            if (arr[index] <= num)
                swap(arr, index, ++lessEqual);
            index++;
        }
        swap(arr, index, ++lessEqual);

        return lessEqual;
    }

    // 划分小区、等区、大区
    public static int[] partition2(int[] arr, int left, int right) {
        // 特殊处理
        if (left > right)  // 不是有效边界
            return new int[]{-1, -1};
        if (left == right)  // 只有一个数
            return new int[]{left, right};

        int num = arr[right];  // 以arr[right]为基准
        int less = left - 1;  // 小区右边界 0 -1
        int more = right;  // 大区左边界  ——  right先停止在最后
        int index = left;  // 遍历 0,1,2,3,4...

        while (index < more) {  // 遍历指针和大区右边界碰上 则停止
            if (arr[index] == num)
                index++;                        // index走 => 等区右扩
            else if (arr[index] < num) {
                swap(arr, index++, ++less);     // 交换 小区右扩 index走
                /*swap(arr, index, less + 1); less++; index++;*/
            } else {  // arr[index] > num
                swap(arr, index, --more);       // 交换 大区左扩 index不走
            }
        }
        swap(arr, more, right);                 // 补全最后一步  ——  right和大区第一个交换

        return new int[]{less + 1, more};  // 返回等区左右边界
    }

    // 荷兰国旗问题
    public static int[] netherlandsFlag(int[] arr, int left, int right) {
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = lenRandomValueRandom(25, 10);
        printArray(arr);

        int i = partition1(arr, 0, arr.length - 1);
        printArray(arr);
        System.out.println(i);

        /*int[] lr = partition2(arr, 0, arr.length - 1);
        printArray(arr);
        printArray(lr);*/
    }
}
