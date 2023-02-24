package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 328. 奇偶链表 https://leetcode.cn/problems/odd-even-linked-list/description/
 * @create :2023-02-24 10:53:00
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {

    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode root = head.next;
        ListNode odd = head, even = head.next;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = root;
        return head;
    }
}
