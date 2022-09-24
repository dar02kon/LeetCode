package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 删除排序链表中的重复元素  https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 * @create :2022-09-24 20:10:00
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode a0 = new ListNode(3,null);
        ListNode a1 = new ListNode(3,a0);
        ListNode a2= new ListNode(1,null);
        ListNode a3 = new ListNode(1,a2);
        ListNode a4 = new ListNode(1,a3);
        ListNode h = new ListNode(0,a4);
        ListNode listNode = new RemoveDuplicatesFromSortedList().deleteDuplicates2(h).next;
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 思路：
     * 判断当前节点的值与后一位的值是否相等，
     * 相等则将当前节点与下下一位节点进行连接
     * 不相等指针后移一位
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p!=null){
            if(p.next==null){
                break;
            }
            if(p.val == p.next.val){
                ListNode q = p.next.next;
                p.next.next = null;
                p.next = q;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 递归版本
     * 过几天可能就忘了~~~~~~
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null || head.next == null){//如果链表只有一个头节点则直接返回
            return head;
        }
        if(head.val == head.next.val){//当前节点与下一个节点的值相同
            head.next = deleteDuplicates(head.next.next);//将当前节点的下个节点跳过，指向下下个递归后的结果
            return deleteDuplicates(head);//为了防止删除了下个节点，与下下个节点还是一样，继续递归
        }
        //当前节点与下个节点不一样，继续递归下个节点，递归函数继续判断是否与下下个节点的值一样
        deleteDuplicates(head.next);
        return head;

    }

}
