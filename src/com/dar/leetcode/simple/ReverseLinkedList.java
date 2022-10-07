package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 反转链表 https://leetcode.cn/problems/reverse-linked-list/
 * @create :2022-10-07 19:17:00
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    /**
     * 迭代反转
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode before = null;
        ListNode after = null;
        while (head!=null){
            after = head.next;//记录下一个节点位置
            head.next = before;//反转一个节点
            before = head;//记录反转后链表的头节点
            head = after;//继续反转下一个节点
        }
        return before;//返回反转后的头节点
    }

    /**
     * 递归：
     * 官方的解答：https://leetcode.cn/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


}
