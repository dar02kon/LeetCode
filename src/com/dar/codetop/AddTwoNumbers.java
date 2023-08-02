package com.dar.codetop;

/**
 * @author :wx
 * @description : 2. 两数相加 https://leetcode.cn/problems/add-two-numbers/
 * @create :2023-08-02 21:54:00
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();
        ListNode head = newHead;
        int temp = 0;
        while (l1 != null || l2 != null) {
            int sum = temp;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // 进位
            temp = sum / 10;
            // 本位
            sum = sum % 10;
            head.next = new ListNode(sum);
            head = head.next;
        }
        // 最后可能还有一位
        if (temp != 0) {
            head.next = new ListNode(temp);
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
