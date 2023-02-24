package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 83. 删除排序链表中的重复元素 https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
 * @create :2023-02-24 09:38:00
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                ListNode q = p.next;
                p.next = p.next.next;
                q.next =  null;
            } else {
                p = p.next;

            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates2(head.next);
        return head.next != null && head.val == head.next.val ? head.next : head;
    }
}
