package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 70. 爬楼梯 https://leetcode.cn/problems/climbing-stairs/
 * @create :2023-01-27 09:11:00
 */
public class ClimbingStairs {

    public static void main(String[] args) {

    }

    public int climbStairs(int n) {
        int pre1 = 0, pre2 = 1;
        for (int i = 0; i < n; i++) {
            int num = pre1 + pre2;
            pre1 = pre2;
            pre2 = num;
        }
        return pre2;
    }
}
