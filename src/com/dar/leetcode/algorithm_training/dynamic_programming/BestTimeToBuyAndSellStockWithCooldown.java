package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 309. 最佳买卖股票时机含冷冻期 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 * @create :2023-02-08 09:58:00
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = prices[i] + dp[i - 1][0];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    public int maxProfit2(int[] prices) {
        int dp0 = -prices[0];
        int dp1 = 0;
        int dp2 = 0;
        for (int i = 1; i < prices.length; i++) {
            dp0 = Math.max(dp0, dp2 - prices[i]);
            dp2 = Math.max(dp2, dp1);
            dp1 = prices[i] + dp0;
        }
        return Math.max(dp1, dp2);
    }

}
