package com.dar.codetop;

/**
 * @author :wx
 * @description : 25. K 个一组翻转链表 https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * @create :2023-06-27 21:53:00
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 新加一个头节点，方便遍历
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 要反转区域的前一个节点
        ListNode pre = newHead;
        // 要反转区域的后一个节点
        ListNode next = head;
        // 反转区域的第一个节点
        ListNode start;
        // 反转区域的最后一个节点
        ListNode end = newHead;
        while (next != null) {
            // 循环k次，找到反转区域的最后一个节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            // 记录反转区域的后一个节点
            next = end.next;
            // 断开连接，方便反转
            end.next = null;
            // 记录反转区域的第一个节点
            start = pre.next;
            /**
             * 反转后，start与end交换位置
             */
            pre.next = reverse(start);
            // 连接链表，便于下次循环
            start.next = next;
            // 更新要反转区域的前一个节点
            pre = start;
            // 重新定位end指针
            end = start;
        }
        return newHead.next;
    }

    /**
     * 反转链表
     */
    private ListNode reverse(ListNode start) {
        if (start == null || start.next == null) {
            return start;
        }
        ListNode head = reverse(start.next);
        start.next.next = start;
        start.next = null;
        return head;
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
