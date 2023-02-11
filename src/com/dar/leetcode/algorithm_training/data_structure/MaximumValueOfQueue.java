package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 面试题59 - II. 队列的最大值 https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/description/
 * @create :2023-02-11 11:21:00
 */
public class MaximumValueOfQueue {

    public static void main(String[] args) {

    }
}

class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> maxDeque;
    public MaxQueue() {
        queue = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public int max_value() {
        if(maxDeque.isEmpty()){
            return -1;
        }
        return maxDeque.peekFirst();
    }

    public void push_back(int value) {
        while (!maxDeque.isEmpty()&&maxDeque.peekLast()<value){
            maxDeque.pollLast();
        }
        queue.add(value);
        maxDeque.addLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        Integer poll = queue.poll();
        if(poll.equals(maxDeque.peekFirst())){
            maxDeque.pollFirst();
        }
        return poll;
    }
}
