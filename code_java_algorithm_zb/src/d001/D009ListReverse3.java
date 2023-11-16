package d001;

public class D009ListReverse3 {
    public static void main(String[] args) {

    }

    // 双链表节点
    public static class DoubleListNode {
        public int value;
        public DoubleListNode last;
        public DoubleListNode next;

        public DoubleListNode(int v) {
            value = v;
        }
    }

    // 双链表的反转
    public static DoubleListNode reverseDoubleList(DoubleListNode head) {
        DoubleListNode pre = null;
        DoubleListNode next = null;
        while (head != null) {
            next = head.next;   // next记住后一个位置
            head.next = pre;    // 反转
            head.last = next;   // 反转
            pre = head;         // pre记住前一个位置
            head = next;        // head往下走
        }
        return pre;
    }
}
