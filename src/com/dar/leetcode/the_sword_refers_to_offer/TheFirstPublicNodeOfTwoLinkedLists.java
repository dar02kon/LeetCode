package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 52. 两个链表的第一个公共节点 https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/?favorite=xb9nqhhg
 * @create :2022-12-13 16:45:00
 */
public class TheFirstPublicNodeOfTwoLinkedLists {

    public static void main(String[] args) {

    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode head = headA;
        while (head != null) {
            lengthA++;
            head = head.next;
        }
        head = headB;
        while (head != null) {
            lengthB++;
            head = head.next;
        }
        while (lengthA != lengthB) {
            if (lengthA < lengthB) {
                lengthA++;
                headB = headB.next;
            } else {
                lengthB++;
                headA = headA.next;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p!=q) {
            p = p==null?headB:p.next;
            q = q==null?headA:q.next;
        }
        return p;
    }
}
