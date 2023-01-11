package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 141. 环形链表 https://leetcode.cn/problems/linked-list-cycle/
 * @create :2023-01-11 09:37:00
 */
public class LinkedListCycle {

    public static void main(String[] args) {

    }

    /**
     * 起点不同
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 起点相同
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null) return false;
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
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}