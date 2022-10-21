package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 删除链表的倒数第 N 个结点 https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * @create :2022-10-21 10:34:00
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {

    }

    /**
     * 遍历两次
     * 先确定链表长度，再确定从前往后的位置
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode p = head;
        while (p!=null){
            length++;
            p = p.next;
        }
        length = length-n+1;
        if(length==n){
            return head.next;
        }
        p = head;
        while (head!=null){
            length--;
            if(length==1){
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }
        return p;
    }

    /**
     * 双指针（快慢指针）
     * 快指针指向head，慢指针指向head之前的虚头节点
     * 快指针先移动n次，之后快慢指针保持距离为n一起移动
     * 快指针为null时，慢指针刚好在倒数n位的前一位
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode root = new ListNode();
        root.next = head;
        ListNode first = head;
        ListNode second = root;
        for (int i = 0; i < n ; i++) {
            first = first.next;
        }
        while (first!=null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return root.next;
    }
}
