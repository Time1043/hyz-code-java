package d011;

public class D013QueueStackAndCircularQueue3 {
    public static void main(String[] args) {

    }
    // 设计循环队列
    public static class CircularQueue {
        public int[] queue;
        public int l, r, size, limit;

        // 同时在队列中的数字个数不要超过k
        public CircularQueue(int k) {
            queue = new int[k];
            l = r = size = 0;
            limit = k;
        }

        // 若队列满了，什么也不做，返回false
        // 若队列没满，加入value，返回true
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            } else {
                queue[r] = value;
                r = r == limit - 1 ? 0 : (r + 1);  // r++ 结束了 跳回0
                size++;
                return true;
            }
        }

        // 若队列空了，什么也不做，返回false
        // 若队列没空，弹出头部数，返回true
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            } else {
                l = l == limit - 1 ? 0 : (l + 1);  // l++ 结束了 跳回0
                size--;
                return true;
            }
        }

        // 返回队列头部数字 但不弹出  若没有返回-1
        public int Front() {
            if (isEmpty()) {
                return -1;
            } else {
                return queue[l];
            }
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            } else {
                int last = r == 0 ? (limit - 1) : (r - 1);
                return queue[last];
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }
}
