package com.dar.codetop;

/**
 * @author :wx
 * @description : 206. 反转链表 https://leetcode.cn/problems/reverse-linked-list/description/
 * @create :2023-06-26 22:03:00
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    /**
     * 反转链表 递归
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 返回尾节点（反转后的头节点）
        ListNode newHead = reverseList(head.next);
        // 反转链表，从后往前
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转链表 迭代
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode before = null;
        ListNode after = null;
        // 从前往后依次反转
        while (head != null) {
            // 记录下一个节点位置
            after = head.next;
            // 反转
            head.next = before;
            // 前一个节点（反转后下一个节点的next）
            before = head;
            // 向后移动
            head = after;
        }
        return before;
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
