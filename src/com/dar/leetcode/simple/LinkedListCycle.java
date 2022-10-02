package com.dar.leetcode.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :wx
 * @description : 环形链表 https://leetcode.cn/problems/linked-list-cycle/
 * @create :2022-10-02 19:42:00
 */
public class LinkedListCycle {

    public static void main(String[] args) {

    }

    /**
     * 比较呆的写法，
     * 循环遍历这个链表的同时循环遍历前面遍历过的链表
     * 若出现相同的节点则存在环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head==null){
            return false;
        }
        ListNode root = head;
        while (true){
            if(head.next==null){
                return false;
            }
            if(head == head.next){
                return true;
            }
            ListNode p = root;
            while (true){
                if(head.next==p){
                    return true;
                }
                if(p==head){
                    break;
                }
                p = p.next;
            }
            head = head.next;
        }
    }

    /**
     * 使用HashSet来存储遍历过的节点
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    /**
     * Floyd 判圈算法（龟兔赛跑算法）
     * 快慢指针，慢指针初始位置指向head，每次向前移动一个位置
     * 快指针初始位置指向head.next，每一次向前移动两个位置
     * 当慢指针追上快指针（或者说快指针相当于操场跑圈一样又追上了慢指针），则存在环
     * @param head
     * @return
     */
    public boolean hasCycle3(ListNode head) {
       if(head==null || head.next==null){
           return false;
       }
       ListNode slow = head;
       ListNode fast = head.next;
       while (slow!=fast){
          if(fast==null || fast.next==null){
              return false;
          }
          slow = slow.next;
           fast = fast.next.next;
       }
       return true;
    }


}
