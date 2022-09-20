package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description :合并两个有序链表  https://leetcode.cn/problems/merge-two-sorted-lists/
 * @create :2022-09-20 20:20:00
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode list4 = new ListNode(3,null);
        ListNode list3 = new ListNode(2,list4);
        ListNode list1 = new ListNode(1,list3);

        ListNode list2 = new ListNode(2,null);
        ListNode listNode = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 递归：一看就懂，一写就蒙
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 直接遍历
     * 需要创建一个头节点指向表头和一个尾节点指向表尾
     * 根据比较结果把值小的节点添加到链表末尾，（要保证尾指针始终在末尾）
     * 当list1与list2出现为空时循环结束，将不为空的链表之间添加到末尾
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode current = result;
        while(list1!=null&&list2!=null)
        {
            if(list1.val<=list2.val){
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if(list1==null){
            current.next=list2;
        }
        if(list2==null){
            current.next=list1;
        }
        return result.next;
    }

}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }