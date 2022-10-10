package com.dar.leetcode.medium;
/**
 * @author :wx
 * @description : 两数相加 https://leetcode.cn/problems/add-two-numbers/
 * @create :2022-10-10 15:46:00
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

    }

    /**
     * 链表不够长就补0节点
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();//头节点
        ListNode cur  = result;
        int digital = 0;//进位
        int total;// 两数相加之和
        while(l1!=null || l2!=null){
            if(l1==null){
                l1 = new ListNode(0);
            }
            if(l2==null){
                l2 = new ListNode(0);
            }
            total = l1.val +l2.val+digital;
            cur.next = new ListNode( total % 10);
            cur = cur.next;
            digital = total / 10 ;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(digital!=0){
            cur.next = new ListNode(digital);
        }
        return result.next;
    }

    /**
     * 稍微优化一下
     * 取两个变量来记录l1与l2的值，为空则赋值为0
     * 在移动l1与l2指针前需要判空
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();//伪指针，指向新链表头指针
        ListNode cur  = result;
        int digital = 0;//进位
        int total;// 两数相加之和
        while(l1!=null || l2!=null){
            int m = l1==null?0:l1.val;
            int n = l2==null?0:l2.val;
            total = m + n +digital;
            cur.next = new ListNode( total % 10);
            cur = cur.next;//指向链表末尾
            digital = total / 10 ;//取进位
            l1 = l1==null?null:l1.next;//l1不为空，移动一位
            l2 = l2==null?null:l2.next;//l2不为空，移动一位
        }
        if(digital!=0){//结果的位数大于l1与l2的表长
            cur.next = new ListNode(digital);//再补一位
        }
        //返回链表的头节点
        return result.next;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
