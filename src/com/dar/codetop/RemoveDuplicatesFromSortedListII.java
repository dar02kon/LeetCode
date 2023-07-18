package com.dar.codetop;

/**
 * @author :wx
 * @description : 82. 删除排序链表中的重复元素 II https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 * @create :2023-07-18 21:40:00
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 用于连接不重复的节点
        ListNode pre = newHead;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                // 没有重复
                pre = pre.next;
            } else {
                // 有重复，删除重复节点，
                // pre指针不能移到，需要保证pre所在节点一定不充分
                pre.next = cur.next;
            }
            cur = cur.next;
        }
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
