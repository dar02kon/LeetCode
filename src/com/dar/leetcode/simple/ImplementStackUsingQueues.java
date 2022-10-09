package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 用队列实现栈 https://leetcode.cn/problems/implement-stack-using-queues/
 * @create :2022-10-09 10:48:00
 */
public class ImplementStackUsingQueues {

    public static void main(String[] args) {

    }

}

/**
 * 使用List集合来实现
 */
class MyStack {
    List<Integer>list;
    public MyStack() {
        list = new LinkedList<>();
    }
    public void push(int x) {
        list.add(x);
    }
    public int pop() {
        return list.remove(list.size()-1);
    }
    public int top() {
        return list.get(list.size()-1);
    }
    public boolean empty() {
        return list.size() <= 0;
    }
}

/**
 * 使用单个队列来实现（可以理解为循环队列）
 * 插入的复杂度为O(n),每一次插入都要做反转
 */
class MyStack2 {
    Queue<Integer> queue;
    public MyStack2() {
        queue = new LinkedList<>();
    }
    public void push(int x) {
        int n = queue.size();
        queue.add(x);
        for (int i = 0; i < n ; i++) {
            Integer poll = queue.poll();
            queue.add(poll);
        }
    }
    public int pop() {
        return queue.poll();
    }
    public int top() {
        return queue.peek();
    }
    public boolean empty() {
        return queue.size() <= 0;
    }
}
