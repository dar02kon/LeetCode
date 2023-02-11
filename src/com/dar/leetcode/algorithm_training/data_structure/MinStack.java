package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Stack;

/**
 * @author :wx
 * @description : 155. 最小栈 https://leetcode.cn/problems/min-stack/description/
 * @create :2023-02-11 10:47:00
 */
public class MinStack {

    public static void main(String[] args) {

    }

}
class MinStack2 {
    Stack<Integer> input;
    Stack<Integer> min;
    public MinStack2() {
        input = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        input.push(val);
        if(min.isEmpty()){
            min.push(val);
        } else {
            min.push(Math.min(min.peek(),val));
        }
    }

    public void pop() {
        if(!input.isEmpty()){
            input.pop();
            min.pop();
        }
    }

    public int top() {
        return input.isEmpty()?-1:input.peek();
    }

    public int getMin() {
        return min.isEmpty()?-1:min.peek();
    }
}