package SortingAlgorithmV2.DivideAndConquer;

import static SortingAlgorithmV2.ArrayComparatorTool.*;

public class QuickSort0NetherlandsFlag {
    // 划分小等区、大区
    public static int partition(int[] arr, int left, int right) {
        if (left > right)
            return -1;
        if (left == right)
            return left;

        int lessEqual = left - 1;  // 小等区右边界 0 -1
        int index = left;  // 遍历 0,1,2,3,4...

        while (index < right) {
            if (arr[index] <= arr[right])  // 以right为基准
                swap(arr, index, ++lessEqual);
            index++;
        }
        swap(arr, ++lessEqual, right);
        return lessEqual;
    }

    // 荷兰国旗问题：划分小区、等区、大区
    public static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right)
            return new int[]{-1, -1};
        if (left == right)
            return new int[]{left, right};

        int num = arr[right];  // 以arr[right]为基准
        int less = left - 1;  // 小区右边界 0 -1
        int more = right;  // 大区左边界 arr.len-1 +1
        /*int more = right + 1;  // 大区左边界 ?????*/
        int index = left;  // 遍历 1,2,3,4...

        while (index < more) {
            if (arr[index] == num)
                index++;                        // index走 => 等区右扩
            else if (arr[index] < num) {
                /*swap(arr, left + 1, index);
                less++;
                index++;*/
                swap(arr, index++, ++less);     // index走 小区右扩
            } else {
                swap(arr, index, --more);       // index不走 大区左扩
            }
        }
        swap(arr, more, right);                 // 补全最后一步 防止初始状态的越界
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr =lenRandomValueRandom(10,10);
        printArray(arr);
        int[] lr = netherlandsFlag(arr, 0, arr.length - 1);
        printArray(arr);
        printArray(lr);
    }
}
