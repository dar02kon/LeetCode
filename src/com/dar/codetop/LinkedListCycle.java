package com.dar.codetop;

/**
 * @author :wx
 * @description : 141. 环形链表 https://leetcode.cn/problems/linked-list-cycle/description/
 * @create :2023-07-10 22:36:00
 */
public class LinkedListCycle {
    /**
     * 快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
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
