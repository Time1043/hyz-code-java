package d011;

import com.sun.org.apache.bcel.internal.generic.LDC;

public class D012PartitionList {
    public static void main(String[] args) {

    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode partition(ListNode head, int x) {
        // 左区 右区
        ListNode leftHead = null, leftTail = null;
        ListNode rightHead = null, rightTail = null;
        ListNode next = null;

        // 扫描原链表的节点 head
        while (head != null) {
            next = head.next;   // next先记下一个节点位置
            head.next = null;   // 断开原链表的节点连接head
            if (head.val < x) {
                if (leftHead == null) {
                    leftHead = head;
                } else {
                    leftTail.next = head;
                }
                leftTail = head;
            } else {
                if (rightHead == null) {
                    rightHead = head;
                } else {
                    rightTail.next = head;
                }
                rightTail = head;
            }
            head = next;    // 连接挂好
        }

        if (leftHead == null) {
            return rightHead;
        }
        leftTail.next = rightHead;  // 左尾连右头  两个链表变一个链表
        return leftHead;
    }
}
