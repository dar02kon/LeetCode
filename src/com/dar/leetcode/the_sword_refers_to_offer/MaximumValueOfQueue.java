package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 面试题59 - II. 队列的最大值 https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/?favorite=xb9nqhhg
 * @create :2023-01-05 09:20:00
 */
public class MaximumValueOfQueue {

    public static void main(String[] args) {

    }


}

class MaxQueue {
    private final Queue<Integer> queue;
    private final Deque<Integer> maxQueue;

    public MaxQueue() {
        this.queue = new LinkedList<>();
        this.maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        while (!maxQueue.isEmpty()&&maxQueue.peekLast()<value){
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);
        queue.offer(value);

    }

    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        int poll = queue.poll();
        if(poll==maxQueue.peekFirst()){
            maxQueue.pollFirst();
        }
        return poll;
    }
}