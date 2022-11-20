package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 18. 删除链表的节点 https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-20 10:38:00
 */
public class DeleteTheNodesOfTheLinkedList {

    public static void main(String[] args) {

    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode root =new ListNode();
        root.next = head;
        ListNode p = root;
        while (head!=null){
            if(head.val==val){
                root.next = head.next;
                head.next=null;
                break;
            }
            head = head.next;
            root = root.next;
        }
        return p.next;
    }

    public ListNode deleteNode2(ListNode head, int val) {
       if(head.val==val){
           return head.next;
       }
       ListNode left = head;
       ListNode right = head.next;
       while (right!=null){
           if(right.val==val){
               left.next = right.next;
               right.next = null;
               break;
           }
           right = right.next;
           left = left.next;
       }
       return head;
    }
}
