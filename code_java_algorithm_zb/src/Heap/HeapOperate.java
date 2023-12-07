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
}
