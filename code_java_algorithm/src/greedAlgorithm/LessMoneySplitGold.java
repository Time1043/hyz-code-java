package greedAlgorithm;

import java.util.PriorityQueue;

public class LessMoneySplitGold {
    // 随机生成数据
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }


    // 暴力解：枚举任何两个
    public static int lessMoneyViolence(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, 0);
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 1) return pre;
        int ans = Integer.MAX_VALUE;  // 初始化 后面取小函数
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) ans[ansi++] = arr[arri];
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }


    // 贪心策略
    public static int lessMoneyGreed(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0, cur = 0;
        while (pQ.size() > 1) {             // 结束条件：堆中只剩一个数
            cur = pQ.poll() + pQ.poll();    // 每一次弹出两个数 合成一个数(非叶节点)
            sum += cur;                     // 将合成数累加到sum
            pQ.add(cur);                    // 将合成数仍回小根堆
        }
        return sum;
    }


    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;

        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoneyViolence(arr) != lessMoneyGreed(arr)) System.out.println("Oops");
        }
        System.out.println("finish");
    }
}
