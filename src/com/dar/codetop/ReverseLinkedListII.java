package com.dar.codetop;

/**
 * @author :wx
 * @description : 92. 反转链表 II https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * @create :2023-07-05 21:20:00
 */
public class ReverseLinkedListII {
    /**
     * 将链表分为三个区域，left前，left-right，right后
     * 定义三个指针，pre用于指向第一个区域末尾，cur用于遍历反转区域，next用于标记遍历的下一个节点
     * 对于反转区域，每遍历一个节点，就将该节点移到到该区域首部，节点指针需要做对应变化
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode pre = newHead;
        // 找到第一个区域末尾节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        // 反转区域
        for (int i = 0; i < right - left; i++) {
            // 记录下一个节点
            next = cur.next;
            // 连接下一个节点
            cur.next = next.next;
            // 将当前节点移动到反转区域首部
            next.next = pre.next;
            // 与第一个区域相连
            pre.next = next;
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
