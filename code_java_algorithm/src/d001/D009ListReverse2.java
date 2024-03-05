package d001;

public class D009ListReverse2 {
    public static void main(String[] args) {

    }

    // 单链表节点
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

    // 单链表的反转
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;  // 前一个
        ListNode next = null;  // 下一个
        while (head != null) {
            next = head.next;   // 先用next记住后面位置
            head.next = pre;    // 反转指向
            pre = head;         // 反转后pre来到head位置
            head = next;        // head往下走 走到next位置
        }
        return pre;
    }
}
