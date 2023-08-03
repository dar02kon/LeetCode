package com.dar.codetop;

/**
 * @author :wx
 * @description : 19. 删除链表的倒数第 N 个结点 https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * @create :2023-08-03 22:08:00
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode fast = head;
        // slow从新节点开始，循环结束后，slow应位于要删除节点的前一个节点
        ListNode slow = newHead;
        // 让fast先走n位
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // fast与slow一起走，fast为空时，slow位于要删除节点的前一个节点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return newHead.next;
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
