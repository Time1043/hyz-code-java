package d011;

public class D011AddTwoNumbers1 {
    public static void main(String[] args) {
        // 342：2->4->3
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        // 465：5->6->4
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        // addTwoNumber
        ListNode result1 = addTwoNumbers(l1, l2);
        printList(result1);
        // multiplyTwoNumbers
        ListNode result2 = multiplyTwoNumbers(l1,l2);
        printList(result2);
    }

    private static void printList(ListNode result) {
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print("->");
            result = result.next;
        }
        System.out.println();
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

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        ListNode ans = null, cur = null;
        int carry = 0;  // 进位信息
        for (
                int sum, val;                   // 声明变量
                head1 != null || head2 != null; // 进行条件  (终止条件：h1和h2都空)
                head1 = head1 == null ? null : head1.next,          // 每一步head1的跳转
                        head2 = head2 == null ? null : head2.next   // 每一步head2的跳转
        ) {
            sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            val = sum % 10;
            carry = sum / 10;
            if (ans == null) {  // 新建答案链表
                ans = new ListNode(val);
                cur = ans;
            } else {            // 新建答案链表的一个新节点
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }
        if (carry == 1) {   // 假如到最后还有进位信息
            cur.next = new ListNode(1);
        }
        return ans;
    }

    public static ListNode multiplyTwoNumbers(ListNode head1, ListNode head2) {
        ListNode ans = new ListNode(0);
        ListNode p1, p2, cur;
        int carry, i;

        for (
                p1 = head1, i = 0;
                p1 != null;
                p1 = p1.next, i++
        ) {
            cur = ans;
            p2 = head2;
            carry = 0;

            for (int j = 0; j < i; j++) {
                if (cur.next == null) cur.next = new ListNode(0);
                cur = cur.next;
            }
            // 乘法和累加
            while (p2 != null || carry > 0) {
                int mul = (p2 != null ? p2.val : 0) * p1.val + carry;
                if (cur.next == null) cur.next = new ListNode(0);
                mul += cur.next.val;

                cur.next.val = mul % 10;  //更新结果链表的当前位
                carry = mul / 10;  // 计算进位信息

                if (p2 != null) p2 = p2.next;
                cur = cur.next;
            }
        }
        return removeLeadingZeros(ans.next);
    }

    private static ListNode removeLeadingZeros(ListNode head) {
        while (head != null && head.val == 0 && head.next != null) head = head.next;
        return head;
    }


}
