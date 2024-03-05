package d011;

import java.util.LinkedList;
import java.util.Queue;

public class D013QueueStackAndCircularQueue1 {
    // 用java内部实现的双向链表  常熟操作慢
    public static class Queue1 {
        public Queue<Integer> queue = new LinkedList<>();

        // 调用任何方法前 先判断该队列内是否有东西
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        // 向队列的尾巴加入num
        public void offer(int num) {
            queue.offer(num);
        }

        // 从队列的头部拿
        public int poll() {
            return queue.poll();
        }

        // 返回队列头部元素 但不弹出
        public int peek() {
            return queue.peek();
        }

        // 返回目前队列中有几个数
        public int size() {
            return queue.size();
        }
    }

    // 单链表的节点结构
    public static class ListNode<V> {
        public V value;
        public ListNode<V> next;

        public ListNode(V v) {
            value = v;
            next = null;
        }
    }

    // 用单链表实现队列
    public static class Queue2<V> {
        private ListNode<V> head;
        private ListNode<V> tail;
        private int size;

        public Queue2() {
            head = null;
            tail = null;
            size = 0;
        }

        // 调用任何方法前 先判断该队列内是否有东西
        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 向队列的尾巴加入num
        public void offer(V value) {
            ListNode<V> cur = new ListNode<V>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        // 从队列的头部拿
        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        // 返回队列头部元素 但不弹出
        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }


    // 更好的写法：需要明确数据量  常数时间好
    public static class Queue3 {
        public int[] queue;
        public int l;
        public int r;

        // 必须明确加入操作的总次数的上限
        public Queue3(int n) {
            queue = new int[n];
            l = 0;
            r = 0;
        }

        // 调用任何方法前 先判断该队列内是否有东西
        public boolean isEmpty() {
            return l == r;
        }

        // 向队列的尾巴加入num
        public void offer(int num) {
            queue[r++] = num;
        }

        // 从队列的头部拿
        public int poll() {
            return queue[l++];
        }

        // 拿到队列的头元素
        public int head() {
            return queue[1];
        }

        // 拿到队列的尾元素
        public int tail() {
            return queue[r - 1];
        }

        public int size() {
            return r - l;
        }
    }
}
