package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 160. 相交链表 https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * @create :2023-02-23 09:16:00
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != null && pB != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
            if (pA == null && pB != null) {
                pA = headB;
            }
            if (pB == null && pA != null) {
                pB = headA;
            }
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
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
