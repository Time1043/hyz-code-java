package SortingAlgorithm;

public class ArrayTool {
    // 打印数组
    public static void printArray(int[] arr) {
        if (arr == null)
            return;

        for (int i = 0; i < arr.length; i++) {
            System.out.print(i != arr.length - 1 ? arr[i] + ", " : arr[i]);
        }
        System.out.println();
    }

    // 交换行为
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];  // a=a^b
        arr[j] = arr[i] ^ arr[j];  // b=a^b  b=a^b^b  b=a
        arr[i] = arr[i] ^ arr[j];  // a=a^b  a=a^a^b  a=b
    }

    // 拷贝数组内容
    public static int[] copyArray(int[] arr) {
        if (arr == null)
            return null;

        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
