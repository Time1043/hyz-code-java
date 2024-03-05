package d011;

import java.util.Stack;

public class D013QueueStackAndCircularQueue2 {
    // 用java内部实现的stack 动态数组  常数时间慢
    public static class Stack1 {
        public Stack<Integer> stack = new Stack<>();

        // 调用任何方法前 先判断该栈内是否有东西
        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int num) {
            stack.push(num);
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public int size() {
            return stack.size();
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

    // 用单链表实现栈
    public static class Stack2<V> {
        private ListNode<V> head;
        private int size;

        public Stack2() {
            head = null;
            size = 0;
        }

        // 调用任何方法前 先判断该栈内是否有东西
        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            ListNode<V> cur = new ListNode<>(value);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {
            return head != null ? head.value : null;
        }
    }

    // 用数组实现  时间复杂度好
    public static class Stack3 {
        public int[] stack;
        public int size;

        // 同时在栈中的元素个数不会超过n
        public Stack3(int n) {
            stack = new int[n];
            size = 0;
        }

        // 调用任何方法前 先判断该栈内是否有东西
        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int num) {
            stack[size++] = num;
        }

        public int pop() {
            return stack[--size];
        }

        public int peek() {
            return stack[size - 1];
        }

        public int size() {
            return size;
        }
    }
}
