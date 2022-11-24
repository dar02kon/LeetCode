package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 25. 合并两个排序的链表 https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-24 14:48:00
 */
public class MergeTwoSortedLinkedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){//先添加比较小的节点
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1==null?l2:l1;//不为空的直接与尾部连接
        return head.next;//返回头节点
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
       if(l1==null){//l1为空，返回l2
           return l2;
       } else if(l2==null){//l2为空返回l1
           return l1;
       } else if(l1.val<l2.val){//都不为空则先添加比较小的节点
           l1.next = mergeTwoLists2(l1.next,l2);
           return l1;
       } else {
           l2.next = mergeTwoLists2(l1,l2.next);
           return l2;
       }
    }

}
