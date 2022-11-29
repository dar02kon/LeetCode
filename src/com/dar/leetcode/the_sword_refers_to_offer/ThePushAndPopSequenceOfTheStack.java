package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Stack;

/**
 * @author :wx
 * @description : 剑指 Offer 31. 栈的压入、弹出序列 https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/?favorite=xb9nqhhg
 * @create :2022-11-29 13:18:00
 */
public class ThePushAndPopSequenceOfTheStack {

    public static void main(String[] args) {

    }

    /**
     * 模拟出栈与入栈
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}
