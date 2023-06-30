package com.dar.codetop;

/**
 * @author :wx
 * @description : 148. 排序链表 https://leetcode.cn/problems/sort-list/description/
 * @create :2023-06-29 21:50:00
 */
public class SortList {
    public static void main(String[] args) {

    }

    /**
     * 递归
     */
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

    /**
     * 迭代
     */
    public ListNode sortList2(ListNode head) {
        int len = getLen(head);
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 分区合并的子链表节点个数，1，2，4直到一整个链表
        for (int i = 1; i < len; i *= 2) {
            // 用于连接合并后的链表（区域排序后的链表）
            ListNode pre = newHead;
            // 链表遍历指针，从头开始
            ListNode cur = newHead.next;
            while (cur != null) {
                /**
                 * 第一个区域
                 */
                // 记录第一个链表的头节点
                ListNode head1 = cur;
                // 遍历找到第二链表
                for (int j = 1; j < i && cur.next != null; j++) {
                    cur = cur.next;
                }
                /**
                 * 第二个区域
                 */
                // 记录第二个链表的头节点
                ListNode head2 = cur.next;
                // 断开，真正形成两个链表
                cur.next = null;
                cur = head2;
                // 遍历找到剩余区域的头节点
                for (int j = 1; j < i && cur != null && cur.next != null; j++) {
                    cur = cur.next;
                }
                /**
                 * 剩余区域
                 */
                // 记录剩余链表的头节点，剩余链表可能为空，需要特殊处理一下
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    // 断开与剩余区域的联系
                    cur.next = null;
                }
                // 合并链表（排序），连接链表（与之前合并的链表进行连接）
                pre.next = merge(head1, head2);
                // pre指针遍历到此次合并的末尾，方便下一次连接
                while (pre.next != null) {
                    pre = pre.next;
                }
                // cur指针指向剩余区域，开始下一个区域的拆分与合并
                cur = next;
            }
        }
        return newHead.next;
    }

    /**
     * 获取链表长度
     */
    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    /**
     * 按顺序合并链表，返回表头
     */
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;
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
