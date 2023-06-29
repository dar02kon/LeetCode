package com.dar.codetop;

/**
 * @author :wx
 * @description : 148. 排序链表 https://leetcode.cn/problems/sort-list/description/
 * @create :2023-06-29 21:50:00
 */
public class SortList {
    public static void main(String[] args) {

    }

    public ListNode sortList(ListNode head) {
        // 为空或只有一个节点直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针，找到中间节点
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中间节点分为两个区域，一左一右
        // 记录右区域起始节点
        ListNode tempSlow = slow.next;
        // 断开链表，划分一左一右两个区域
        slow.next = null;
        // 左区域排序，获取排序后链表头节点
        ListNode left = sortList(head);
        // 右区域排序，获取排序后头节点
        ListNode right = sortList(tempSlow);
        // 合并已经排序的左右两个链表（按顺序合并）
        ListNode tempHead = new ListNode();
        ListNode newHead = tempHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tempHead.next = left;
                left = left.next;
            } else {
                tempHead.next = right;
                right = right.next;
            }
            tempHead = tempHead.next;
        }
        // 有一方为空，直接链接另一方
        tempHead.next = left == null ? right : left;
        // 返回合并后的头节点
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
