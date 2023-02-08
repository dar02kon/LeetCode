package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 338. 比特位计数 https://leetcode.cn/problems/counting-bits/description/
 * @create :2023-02-08 10:57:00
 */
public class CountingBits {

    public static void main(String[] args) {

    }

    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            dp[i] = dp[i&(i-1)]+1;
        }
        return dp;
    }

}
