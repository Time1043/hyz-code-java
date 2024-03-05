package Heap;

import static SortingAlgorithmV2.ArrayComparatorTool.swap;

public class HeapOperate {
    public static class MyMaxHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        // 元素入堆 —— O(logN)
        public void push(int value) {
            if (heapSize == limit)
                throw new RuntimeException("heap is full");
            heap[heapSize] = value;  // 1 value加至堆尾
            heapInsert(heap, heapSize++);  // 2 调整堆：value与父节点比较
        }

        private void heapInsert(int[] arr, int index) {
            /* 堆底乱时 往上调整堆 */
            // 停止条件：index不必父节点大、或index已经来到顶部
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;  // 迭代
            }
        }

        // 堆顶出堆 —— O(logN)
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);  // 1 交换至堆尾后弹出
            heapify(heap, 0, heapSize);  // 2 调整堆：中左右比较
            return ans;
        }

        private void heapify(int[] arr, int index, int heapSize) {
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

        // 访问堆顶元素 —— O(1)
        public int peek() {
            return heap[0];
        }

        // 获取堆的元素数量 —— O(1)
        public int getHeapSize() {
            return heapSize;
        }

        // 判断堆是否为空 —— O(1)
        public boolean isEmpty() {
            return heapSize == 0;
        }

        // 判断堆是否满了 —— O(1)
        public boolean isFull() {
            return heapSize == limit;
        }
    }

    public static class MaxHeapViolence {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MaxHeapViolence(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // O(1)
        public void push(int value) {
            if (heapSize == limit)
                throw new RuntimeException("heap is full");
            heap[heapSize++] = value;
        }

        // O(N)
        public int pop() {
            int maxIndex = 0;
            for (int i = 0; i < heapSize; i++) {
                if (heap[i] > heap[maxIndex])
                    maxIndex = i;
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--heapSize];
            return ans;
        }
    }

    // 对数器
    public static void testFramework() {
        int value = 1000;
        int limit = 100;
        int textTimes = 100000;

        System.out.println("-------------测试开始-------------");
        for (int i = 0; i < textTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap myMaxHeap = new MyMaxHeap(curLimit);
            MaxHeapViolence maxHeapViolence = new MaxHeapViolence(curLimit);
            int curOpTimes = (int) (Math.random() * limit);  // 每一轮随机次操作

            for (int j = 0; j < curOpTimes; j++) {
                if (myMaxHeap.isEmpty() != maxHeapViolence.isEmpty())
                    System.out.println("错误：isEmpty");
                if (myMaxHeap.isFull() != maxHeapViolence.isFull())
                    System.out.println("错误：isFull");

                if (myMaxHeap.isEmpty()) {  // 空的时候
                    int curValue = (int) (Math.random() * value);
                    myMaxHeap.push(curValue);
                    maxHeapViolence.push(curValue);
                    // 二者入堆的结果就是不一样的
                } else if (myMaxHeap.isFull()) {  // 满的时候
                    if (myMaxHeap.pop() != maxHeapViolence.pop())
                        System.out.println("错误：pop");
                } else {  // 不空也不满：0.5概率加数字、0.5概率弹数字
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        myMaxHeap.push(curValue);
                        maxHeapViolence.push(curValue);
                    } else {
                        if (myMaxHeap.pop() != maxHeapViolence.pop())
                            System.out.println("错误：pop");
                    }
                }
            }
        }
        System.out.println("-------------测试结束-------------");
    }

    public static void main(String[] args) {
        testFramework();
    }
}
