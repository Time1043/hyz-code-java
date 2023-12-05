package d001;

public class D001Sort2QuickSort {
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i != arr.length - 1 ? arr[i] + ", " : arr[i]);
        }
        System.out.println();
    }

    // arr[i]和arr[j]交换
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

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
            System.out.println("内部：");
            printArray(arr);
            System.out.println("指针状态i：" + i + "，指针状态j：" + j);
        }
        swap(arr, i, left);  // 将基准数交换至两个子数组的分界线
        System.out.println("外围：");
        printArray(arr);
        System.out.println("指针状态i：" + i + "，指针状态j：" + j);
        System.out.println("基准数索引：" + i + ", 数字：" + arr[i]);
        System.out.println();
        return i;  // 返回基准数的索引
    }

    // 测试函数
    public static void main(String args[]) {
        int[] arr = {35,63,85,29,9,67,22,11,66,39};
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("排序后的数组:");
        printArray(arr);
    }

}
