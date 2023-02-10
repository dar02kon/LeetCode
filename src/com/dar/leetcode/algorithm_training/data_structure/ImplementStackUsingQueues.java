package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 225. 用队列实现栈 https://leetcode.cn/problems/implement-stack-using-queues/description/
 * @create :2023-02-10 13:09:00
 */
public class ImplementStackUsingQueues {
}

class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.isEmpty() ? -1 : queue.poll();
    }

    public int top() {
        return queue.isEmpty() ? -1 : queue.peek();

    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
