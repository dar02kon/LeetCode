package com.dar.codetop;

/**
 * @author :wx
 * @description : 142. 环形链表 II https://leetcode.cn/problems/linked-list-cycle-ii/description/
 * @create :2023-07-23 21:31:00
 */
public class LinkedListCycleII {
    /**
     * 推导一下：设头节点到环的第一个节点的距离为a（a个节点），环的长度为b（b个节点）
     * 快慢指针。fast与slow，fast一次走两个节点，slow一次走一个节点
     * 设fast走的距离为f，slow走的距离为s
     * f = 2s
     * f = s+nb（相遇，fast比slow多走n圈）
     * 相减，s = nb
     * 有环，从头节点开始走nb+a个节点，一定处于环的第一个节点，而slow指针已经走了nb个节点，还差a个
     * 所以将fast移到到头节点，和slow一起走，相遇一定在环的第一个节点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
