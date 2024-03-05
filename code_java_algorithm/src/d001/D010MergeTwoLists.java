package d001;

public class D010MergeTwoLists {
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

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;

        ListNode pre = head;  // 路线 收尾
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;    // 核心连线
                cur1 = cur1.next;       // 继续推进
            } else {
                pre.next = cur2;    // 核心连线
                cur2 = cur2.next;       // 继续推进
            }
            pre = pre.next;             // 继续推进
        }
        pre.next = cur1 != null ? cur1 : cur2;      // cur1和cur2有一个为空  pre->非空链
        return head;                                // 节省内存开销 两个链表穿针引线
    }
}
