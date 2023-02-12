package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Stack;

/**
 * @author :wx
 * @description : 739. 每日温度 https://leetcode.cn/problems/daily-temperatures/
 * @create :2023-02-12 11:07:00
 */
public class DailyTemperatures {

    public static void main(String[] args) {

    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer pop = stack.pop();
                result[pop] = i-pop;
            }
            stack.push(i);
        }
        return result;
    }
}
