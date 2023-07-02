package com.dar.codetop;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :wx
 * @description : 23. 合并 K 个升序链表 https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * @create :2023-07-02 21:25:00
 */
public class MergeKSortedLists {
    /**
     * 分治，从中间划分，两两合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeRange(lists, 0, lists.length - 1);
    }

    /**
     * 分治合并
     */
    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        // 继续分治合并左区域
        ListNode leftNode = mergeRange(lists, left, mid);
        // 继续分治合并右区域
        ListNode rightNode = mergeRange(lists, mid + 1, right);
        // 合并左右区域
        return merge(leftNode, rightNode);
    }

    /**
     * 合并两个链表
     */
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode();
        ListNode head = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }
        head.next = head1 == null ? head2 : head1;
        return newHead.next;
    }

    /**
     * 优先队列
     * 将节点一个一个放入队列中，每次取最小的
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // 优先队列，从小到大排序
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (!priorityQueue.isEmpty()) {
            cur.next = priorityQueue.poll();
            cur = cur.next;
            if (cur.next != null) { // 添加新节点
                priorityQueue.add(cur.next);
            }
        }
        return newHead.next;
    }


    private static class ListNode {
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
}
