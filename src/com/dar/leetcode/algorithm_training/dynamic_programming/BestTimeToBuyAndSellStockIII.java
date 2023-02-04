package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 123. 买卖股票的最佳时机 III https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * @create :2023-02-04 14:01:00
 */
public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int buy1 = prices[0], buy2 = prices[0];
        int sell1 = 0, sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1,prices[i]-buy1);
            buy2 = Math.min(buy2,prices[i]-sell1);
            sell2 = Math.max(sell2,prices[i]-buy2);
        }
        return sell2;
    }
}
