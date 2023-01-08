package com.dar.leetcode.algorithm_training.greedy_thinking;

/**
 * @author :wx
 * @description : 122. 买卖股票的最佳时机 II https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * @create :2023-01-08 10:51:00
 */
public class BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int profit = 0;//利润
        for (int i = 0; i < prices.length-1; i++) {
            if(prices[i]<prices[i+1]) profit+=prices[i+1]-prices[i];//收益和
        }
        return profit;
    }
}
