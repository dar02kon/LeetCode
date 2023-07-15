package com.dar.codetop;

/**
 * @author :wx
 * @description : 121. 买卖股票的最佳时机 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @create :2023-07-15 18:45:00
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        // 当前能获取的最大利润
        int max = 0;
        // 记录当前股票最低价
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 更新股票最低价
            min = Math.min(prices[i], min);
            // 更新最大利润
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }
}
