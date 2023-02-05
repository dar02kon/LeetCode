package com.dar.leetcode.algorithm_training.dynamic_programming;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 188. 买卖股票的最佳时机 IV https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/
 * @create :2023-02-05 19:18:00
 */
public class BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {

    }

    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, prices[0]);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.min(buy[j], prices[i] - sell[j - 1]);
                sell[j] = Math.max(sell[j], prices[i] - buy[j]);
            }
        }
        return sell[k];
    }
}
