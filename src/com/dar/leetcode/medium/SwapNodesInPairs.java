package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 两两交换链表中的节点 https://leetcode.cn/problems/swap-nodes-in-pairs/
 * @create :2022-10-23 18:52:00
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {

    }

    /**
     * 迭代遍历
     * 需要创建一个伪头节点root指向head，便于循环
     * 设置两个指针指向需要反转的两个节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){//链表为空或只存在一个节点则直接返回
            return head;
        }
        ListNode root = new ListNode(0,head);//伪头节点
        ListNode p = head;//需要反转的第一个节点
        ListNode q = head.next;//需要反转的第二个节点
        head = q;//head指向最终的头节点，肯定是原本的第二个节点
        while (q!=null){
            p.next = q.next;//第一个节点的next指针指向第三个节点
            q.next = p;//第二个节点的next指针指向第一个节点
            root.next = q;//保持链表不断
            root = p;//指针下一组的前一个节点
            p = p.next;//指针后移
            if(p==null){//为空则直接返回
                return head;
            }
            q = p.next;//指针后移
        }
        return head;//返回头节点
    }

    /**
     * 将上面的方法写的好看一些
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if(head==null||head.next==null){//链表为空或只存在一个节点则直接返回
            return head;
        }
        ListNode root = new ListNode(0,head);//伪头节点
        head = root.next.next;
        ListNode p;
        ListNode q;
        while (root.next!=null&&root.next.next!=null){
            p = root.next;//第一个节点
            q = root.next.next;//第二个节点
            root.next = q;//上一组的尾节点的next指针指向这一组反转后的头节点，即第二个节点
            p.next = q.next;//指向下组反转前的头节点
            q.next = p;//反转，第二个节点的next指针指向第一个节点
            root = p;//移动尾指针
        }
        return head;//返回头节点
    }


    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head){
        if(head==null||head.next==null){
            return head;
        }
        ListNode node = head.next;//指向第二个节点
        head.next = swapPairs2(node.next);//指向下一组返回的头节点
        node.next = head;//反转，第二个节点的next指针指向第一个节点
        return node;//返回反转后的头节点
    }
}
