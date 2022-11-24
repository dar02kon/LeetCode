package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 22. 链表中倒数第k个节点 https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-24 10:18:00
 */
public class TheKTHLastNodeInTheList {
    public static void main(String[] args) {

    }

    /**
     * 快慢指针
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        while (k>0){
            right = right.next;
            k--;
        }
        while (right!=null){
            left = left.next;
            right = right.next;
        }
        return left;
    }
}
