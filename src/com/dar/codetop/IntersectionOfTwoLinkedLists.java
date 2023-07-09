package com.dar.codetop;

/**
 * @author :wx
 * @description : 160. 相交链表 https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * @create :2023-07-09 22:59:00
 */
public class IntersectionOfTwoLinkedLists {
    /**
     * 类似与操场跑圈，设置两指针p，q分别指向A和B链表，
     * 当两个指针走过的节点数等于A+B，则要么相遇要么都走到头
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode listNodeA = headA;
        ListNode listNodeB = headB;
        while (listNodeA != listNodeB) {
            // 如果listNodeA已经遍历完A链表，且listNodeA != listNodeB，
            // 则listNodeA开始遍历B链表
            listNodeA = listNodeA == null ? headB : listNodeA.next;
            listNodeB = listNodeB == null ? headA : listNodeB.next;
        }
        return listNodeA;
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
