package com.dar.leetcode.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :wx
 * @description : 相交链表 https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * @create :2022-10-03 19:07:00
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {

    }

    /**
     * 使用hash表存储遍历过的节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA!=null){
            set.add(headA);
            headA = headA.next;
        }
        while (headB!=null){
           if(set.contains(headB)) {
               return headB;
           } else {
               headB = headB.next;
           }
        }
        return null;
    }

    /**
     * 先分别获取两个链表的长度
     * 移动头指针使两个链表能达到同步遍历（需要遍历的节点个数相同）
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        if(lenA>lenB){
            for (int i = 0; i < lenA-lenB ; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < lenB-lenA ; i++) {
                headB = headB.next;
            }
        }
        while (headA!=null){
            if(headA==headB){
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    /**
     * 获取链表长度
     * @param head
     * @return
     */
    public int getLength(ListNode head){
        int count = 0;
        while (head!=null){
            count++;
            head = head.next;
        }
        return count;
    }

    /**
     * 双指针（相当于第一个链表后面拼接第二个链表，第二个链表后面拼接第一个链表来达到同步遍历）
     * 指针A指向第一个链表的表头，指针B指向第二个链表的表头
     * 移动指针A和B，
     * 如果A指向null，将A指向第二个链表的表头，
     * 如果B指向null，将B指向第一个链表的表头
     * 当A==B则说明存在公共部分，返回A或B
     * 若A==null&&B==null，遍历结束，返回null
     * <p>
     *     证明：
     *     一、两个链表没有公共部分：设第一个链表的长度为a，第二个链表的长度为b，
     *     则循环a+b次后出现A==null&&B==null退出。
     *     二、两个链表有公共部分：、
     *     设第一个链表的非公共部分长度为a，第二个链表的非公共部分长度为b，公共部分长度为c
     *     若a == b，则循环a（或b）次，出现A==B退出
     *     若a != b,则循环a+c+b（或b+c+a）次，出现A==B退出
     * </p>
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
       ListNode A = headA;
       ListNode B = headB;
       while (!(A==null&&B==null)){
           if(A==B){
               return A;
           }
           if(A==null){
               A = headB;
           } else {
               A = A.next;
           }
           if(B==null){
               B = headA;
           } else {
               B = B.next;
           }
       }
       return null;
    }

}
