package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 234. 回文链表 https://leetcode.cn/problems/palindrome-linked-list/description/
 * @create :2023-02-24 10:20:00
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head,slow = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast!=null){//奇数节点
            slow = slow.next;
        }
        // 切断链表
        ListNode root = head;
        while (root.next!=slow) {
            root = root.next;
        }
        root.next = null;

        // 反转后半段链表
        root = slow;
        ListNode newHead = null;
        while (root!=null){
            fast = root.next; // fast指针充当临时变量
            root.next = newHead;
            newHead = root;
            root = fast;
        }
        // 比较，以反转后的链表长度为准，避免奇数位的影响
        // 保留两个链表的头指针，方便恢复原链表
        fast = head;
        slow = newHead;
        while (slow!=null){
            if(slow.val!=fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;

    }

}
