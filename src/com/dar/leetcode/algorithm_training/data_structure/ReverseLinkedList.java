package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 206. 反转链表 https://leetcode.cn/problems/reverse-linked-list/description/
 * @create :2023-02-23 09:37:00
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if(head==null) return null;
        ListNode p = head; // 头节点
        head = head.next;
        p.next = null;
        while (head!=null){ // 反转链表
            ListNode q = head;
            head = head.next;
            q.next = p;
            p = q;
        }
        return p;
    }

    public ListNode reverseList2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode newHead = reverseList2(head.next); // 最后一个节点为头节点
        // 反转链表
        head.next.next = head;
        head.next = null; // 最后一个节点的后继结点需要为空
        return newHead; // 返回头节点
    }
}
