package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 21. 合并两个有序链表 https://leetcode.cn/problems/merge-two-sorted-lists/
 * @create :2023-02-23 09:57:00
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                head.next = list2;
                break;
            }
            if (list2 == null) {
                head.next = list1;
                break;
            }
            if (list1.val > list2.val) {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            } else {
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            }
        }
        return p.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if(list1==null){
            return list2;
        } else if(list2==null){
            return list1;
        } else if(list1.val<list2.val){
            list1.next = mergeTwoLists2(list1.next,list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list2.next,list1);
            return list2;
        }
    }
}
