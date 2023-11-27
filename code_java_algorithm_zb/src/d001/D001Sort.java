package d001;

public class D001Sort {
    /*
     * 插入排序、希尔排序
     * 冒泡排序(提前结束)、快速排序
     *
     * */

    // 打印数组
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
        arr[i] = tmp;
    }

    // 拷贝数组
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 随机生成一个数组arr，数组长度[0,maxLen-1]，数组中的元素值[0,maxValue-1]
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // 判断是否升序
    public static boolean isSorted(int[] arr) {
        if (arr.length < 2) {
            return true;  // 不到两个数 默认升序
        }

        int maxValue = arr[0];  // 不妨设0位置为最大值
        for (int i = 0; i < arr.length; i++) {
            if (maxValue > arr[i]) {  // 后面出现的数居然更小 不是升序
                return false;
            }
            maxValue = Math.max(maxValue, arr[i]);  // 更新最大值
        }
        return true;
    }

    // 冒泡排序(两两比较后交换)：一整轮没有交换则提前结束
    public static void bubbleSort(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        for (int end = n - 1; end > 0; end--) {  // 内循环每次减少一次，因为最大的数已经冒泡到最后
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {  // 比较相邻的两个元素，如果顺序错误就交换它们
                    swap(arr, second, second-1);
                }
            }
        }
    }

    // 选择排序
    public static void selectionSort(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        // 一次循环找到一个最小元素的正确位置
        for (int i = 0; i < n; i++) {
            int minValueIndex = i;  // 找到最小值对应的索引 不妨设
            for (int j = i + 1; j < n; j++) {  // 扫描：最小值索引是否更新
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    // 直接插入排序
    public static void insertionSort(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        for (int end = 1; end < n; end++) {  // end当前关注的数  pre当前关注的数的左一个
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    // 希尔排序：改进的插入排序
    public static void shellSort(int[] arr) {
        int n = arr.length;
        // 逐渐缩减增量
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对各个子列表进行插入排序
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i]; // 取出当前元素
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap]; // 向后移动元素
                }
                arr[j] = temp; // 将当前元素放到正确的位置
            }
        }
    }

    // 快速排序(交换)


    // 归并排序


    // 堆排序

    public static void main(String[] args) {
        // 指定数组的长度范围、值范围
        int maxLen = 30;
        int maxValue = 1000;
        int testTimes = 100000;  // 大样本随机验证

        /*// 单例
        int[] arr1 = lenRandomValueRandom(maxLen,maxValue);
        int[] tmp =copyArray(arr1);  // 在排序前先备份
        printArray(arr1);
        bubbleSort(arr1);
        if (!isSorted(arr1)){
            System.out.println("-----------排序出现错误-----------");
            printArray(tmp);  // 打印错误的具体例子
        }*/

        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] tmp = copyArray(arr1);  // 在排序前先备份

            bubbleSort(arr1);

            if (!isSorted(arr1)) {
                System.out.println("-----------排序出现错误-----------");
                printArray(tmp);  // 打印错误的具体例子
                break;  // 只要有一个错误就终止
            }
        }

        System.out.println("程序执行完毕");
    }
}
