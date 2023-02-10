package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Stack;

/**
 * @author :wx
 * @description : 232. 用栈实现队列 https://leetcode.cn/problems/implement-queue-using-stacks/description/
 * @create :2023-02-10 11:40:00
 */
public class ImplementQueueUsingStacks {

}

class MyQueue {
    Stack<Integer> in;
    Stack<Integer> out;

    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        transform();
        return out.pop();
    }

    public int peek() {
        transform();
        return out.peek();
    }

    public boolean empty() {
        return in.empty() && out.isEmpty();
    }

    private void transform() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

}
