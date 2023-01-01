package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 63. 股票的最大利润 https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-01 14:52:00
 */
public class TheMaximumProfitOfTheStock {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice,price);
            profit = Math.max(profit,price-minPrice);
        }
        return profit;
    }
}
