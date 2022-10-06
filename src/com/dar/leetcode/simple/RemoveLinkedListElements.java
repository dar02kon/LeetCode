package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 移除链表元素 https://leetcode.cn/problems/remove-linked-list-elements/
 * @create :2022-10-06 19:45:00
 */
public class RemoveLinkedListElements {

    public static void main(String[] args) {

    }

    /**
     * 普通思路：
     * 先循环保证头节点的值不等于val
     * 再循环判断每一个节点的值，等于val就改一下指针指向
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        while (head!=null&&head.val==val){
            head = head.next;
        }
        ListNode front = head;
        ListNode current = head;
        while (current!=null){
            if(current.val == val){
                front.next = current.next;
                current = front.next;
            } else {
                front = current;
                current = current.next;
            }
        }
        return head;
    }
}
