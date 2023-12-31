package d001;

import java.util.Arrays;

public class D001Sort {
    /*
     * 插入排序、希尔排序
     * 冒泡排序(提前结束)、快速排序
     *
     * */

    // 打印数组——
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

    public static void swap2(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];  // a=a^b
        arr[j] = arr[i] ^ arr[j];  // b=a^b     b=a^b^b      b=a
        arr[i] = arr[i] ^ arr[j];  // a=a^b     a=a^b^a      a=b
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

    // 结果验：判断是否升序
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

    // 算法B验：系统提供的
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // 算法B验：判断两个结果是否相同
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // 冒泡排序(两两比较后交换)
    public static void bubbleSort(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        // 外循环：未排序区间为 [0, i]
        for (int end = n - 1; end > 0; end--) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {  // 比较相邻的两个元素，如果顺序错误就交换它们
                    swap(arr, second, second - 1);
                }
            }
        }
    }

    // 冒泡排序(两两比较后交换)：一整轮没有交换则提前结束 —— 效率优化
    public static void bubbleSort2(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        boolean flag = false;  // 标志位 检测某轮排序没有交换行为即退出
        // 外循环：未排序区间为 [0, i]
        for (int end = n - 1; end > 0; end--) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {  // 比较相邻的两个元素，如果顺序错误就交换它们
                    swap(arr, second, second - 1);
                }
            }
            if (!flag)
                break;  // 标志位 检测某轮排序没有交换行为即退出
        }
    }

    // 选择排序
    public static void selectionSort(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        // 外循环：未排序区间为 [i, n-1]
        for (int i = 0; i < n; i++) {
            int minValueIndex = i;  // 找到最小值对应的索引 不妨设
            // 内循环：找到未排序区间内的最小元素
            for (int j = i + 1; j < n; j++) {
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
        // 外循环：已排序元素数量为 1, 2, ..., n
        for (int end = 1; end < n; end++) {  // end当前关注的数  pre当前关注的数的左一个
            // 内循环：将 base 插入到已排序部分的正确位置
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
            /*for (int pre = end - 1; pre >= 0; pre--) {
                if (arr[pre] > arr[pre + 1]) {
                    swap(arr, pre, pre + 1);
                    end = pre;
                }
            }*/
        }
    }

    // 直接插入排序
    public static void insertionSort2(int[] arr) {
        // 边界条件：空、非空但数目少于2
        if (arr == null || arr.length < 2) {
            return;  // 不需要排序
        }

        int n = arr.length;
        // 外循环：已排序元素数量为 1, 2, ..., n
        for (int end = 1; end < n; end++) {  // end当前关注的数  pre当前关注的数的左一个
            int base = arr[end], pre = end - 1;
            // 内循环：将 base 插入到已排序部分的正确位置
            while (pre >= 0 && arr[pre] > base) {
                arr[pre + 1] = arr[pre];  // 将arr[pre]向右移动一位
                pre--;
            }
            arr[pre + 1] = base;  // 将base赋值到正确位置
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
        System.out.println("-----------测试开始-----------");
        int maxLen = 30;
        int maxValue = 1000;
        int testTimes = 100000;  // 大样本随机验证

        /*for (int i = 0; i < testTimes; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] tmp = copyArray(arr1);  // 在排序前先备份
            insertionSort(arr1);  // 核心
            if (!isSorted(arr1)) {
                System.out.println("-----------排序出现错误-----------");
                printArray(tmp);  // 打印错误的具体例子
                break;  // 只要有一个错误就终止
            }
        }*/

        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] tmp = copyArray(arr1);  // 在排序前先备份
            insertionSort(arr1);  // 核心
            comparator(tmp);  // 暴力

            if (!isEqual(arr1, tmp)) {
                System.out.println("-----------排序出现错误-----------");
                succeed = false;
                printArray(arr1);
                printArray(tmp);
                break;  // 只要有一个错误就终止
            }
        }
        System.out.println(succeed ? "nice!" : "fucking fucked");

        System.out.println("-----------程序执行完毕-----------");

    }
}
