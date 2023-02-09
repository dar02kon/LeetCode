package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 121. 买卖股票的最佳时机 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 * @create :2023-02-09 11:28:00
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        int min = prices[0];
        for (int price : prices) {
            profit = Math.max(profit,price-min);
            min = Math.min(min,price);
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        int max = 0;
        for (int i = prices.length-1; i >= 0; i--) {
            profit = Math.max(profit,max-prices[i]);
            max = Math.max(prices[i],max);
        }
        return profit;
    }

}
