package com.dar.leetcode.algorithm_training.dynamic_programming;


/**
 * @author :wx
 * @description : 279. 完全平方数 https://leetcode.cn/problems/perfect-squares/description/
 * @create :2023-01-24 15:22:00
 */
public class PerfectSquares {

    public static void main(String[] args) {
        int i = new PerfectSquares().numSquares(12);
        System.out.println(i);
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = n;
            for (int j = 1; j * j <= i; j++) {// 将 i 拆分为 j*j 与 i-j*j
                // j*j为一个完全平方数，它的贡献值固定为1，
                // i-j*j的贡献值由于j的不同有多种结果，取其最小值
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;// 1为 j*j 的贡献值
        }
        return dp[n];
    }
}
