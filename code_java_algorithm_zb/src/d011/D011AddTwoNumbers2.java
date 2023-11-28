package d011;

public class D011AddTwoNumbers2 {
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

    // 求链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        // 复用老的长链表
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1 : head2;  // 长链表头l
        ListNode s = l == head1 ? head2 : head1;    // 短链表头s

        // 三个阶段的划分：l有s有、l有s无、l无s无
        ListNode curL = l;  // 长链表当前节点
        ListNode curS = s;  // 短链表当前节点
        ListNode last = curL;   // 串最终答案
        int carry = 0;      // 进位信息
        int curNum = 0;     // 当前数字运算结果

        while (curS != null) {
            curNum = curL.val + curS.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;

            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;

            last = curL;
            curL = curL.next;
        }
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return l;
    }
}
