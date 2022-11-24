package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 24. 反转链表 https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-24 12:07:00
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode left = null;//反转后之前头节点的next指针需要指向null
        ListNode right;
        while (head != null) {
            right = head.next;//先记录下一个位置
            head.next = left;//反转
            //指针后移
            left = head;
            head = right;
        }
        return left;//返回反转后的头节点
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;//返回新的头节点
        }
        ListNode newHead = reverseList2(head.next);
        //反转
        head.next.next = head;
        head.next = null;
        return newHead;//返回头节点
    }
}
