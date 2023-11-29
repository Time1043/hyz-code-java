package d001;

public class D001Sort2QuickSort {
    // 快速排序的主函数
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi 是分区后的中心索引
            int pi = partition(arr, low, high);

            // 递归调用排序左半部分
            quickSort(arr, low, pi - 1);
            // 递归调用排序右半部分
            quickSort(arr, pi + 1, high);
        }
    }

    // 对数组进行分区的函数
    private static int partition(int[] arr, int low, int high) {
        // 选择最右边的元素作为基准
        int pivot = arr[high];
        int i = (low - 1); // 小于基准的元素的索引

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;

                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 交换 arr[i+1] 和 arr[high] (即基准)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 测试函数
    public static void main(String args[]) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("排序后的数组:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
