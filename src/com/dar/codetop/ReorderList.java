package com.dar.codetop;

/**
 * @author :wx
 * @description : 143. 重排链表 https://leetcode.cn/problems/reorder-list/description/
 * @create :2023-07-12 21:29:00
 */
public class ReorderList {
    /**
     * 目标链表即为将原链表的左半端和反转后的右半端合并后的结果
     */
    public void reorderList(ListNode head) {
        ListNode mid = getMid(head);
        ListNode temp = mid.next;
        mid.next = null;
        ListNode reverse = reverse(temp);
        merge(head, reverse);
    }

    /**
     * 获取链表中间节点（快慢指针）
     */
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 链表合并
     */
    private void merge(ListNode l1, ListNode l2) {
        ListNode temp1 = new ListNode();
        ListNode temp2 = new ListNode();
        while (l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;
            l1.next = l2;
            l1 = temp1;
            l2.next = l1;
            l2 = temp2;
        }
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
