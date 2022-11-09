package com.dar.leetcode.the_sword_refers_to_offer;


/**
 * @author :wx
 * @description : 剑指 Offer 06. 从尾到头打印链表 https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-09 16:46:00
 */
public class PrintALinkedListFromEndToEnd {
    public static void main(String[] args) {

    }

    /**
     * 二次遍历
     * 对链表进行二次遍历，
     * 第一次获取链表长度来进行数组的初始化
     * 第二次填充数组
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;//记录链表长度，同样也是填充数组时的下标
        ListNode p = head;
        while (p!=null){//获取链表长度
            len ++;
            p = p.next;
        }
        int[] result = new int[len];
        p = head;
        len = 0;
        while (p!=null){//填充数组
            result[len++] = p.val;
            p = p.next;
        }
        return result;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
