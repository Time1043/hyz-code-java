package d011;

public class D011AddTwoNumbers1 {
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
}
