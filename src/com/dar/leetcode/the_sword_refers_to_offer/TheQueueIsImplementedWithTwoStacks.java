package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Stack;

/**
 * @author :wx
 * @description : 剑指 Offer 09. 用两个栈实现队列 https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/?favorite=xb9nqhhg
 * @create :2022-11-11 12:01:00
 */
public class TheQueueIsImplementedWithTwoStacks {
}

/**
 * 使用两个栈，一个输出栈，一个输入栈
 */
class CQueue1 {
    private final Stack<Integer> stack;//输出栈
    private final Stack<Integer> target;//输入栈

    public CQueue1() {
        this.stack = new Stack<>();
        this.target = new Stack<>();
    }

    public void appendTail(int value) {
        target.push(value);//添加元素
    }

    public int deleteHead() {
        if (stack.empty()) {//输出栈为空
            if (target.isEmpty()) {//输入栈为空
                return -1;
            }
            while (!target.isEmpty()) {//将输入栈中元素依次取出存入输出栈中
                stack.push(target.pop());
            }
        }
        return stack.pop();//弹出栈顶
    }
}

/**
 * 一个栈（数组）
 */
class CQueue2 {
    private final Stack<Integer> stack;

    public CQueue2() {
        this.stack = new Stack<>();
    }

    public void appendTail(int value) {
        stack.push(value);
    }

    public int deleteHead() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.remove(0);
    }
}
