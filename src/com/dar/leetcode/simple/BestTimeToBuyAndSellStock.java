package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 买卖股票的最佳时机 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @create :2022-10-01 14:27:00
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices =  {7,6,4,3,1};
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit2(prices));
    }

    /**
     * 直接遍历，时间复杂度是 O(n*n)
     * 超时了
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                max = Math.max(max,prices[j]-prices[i]);
            }
        }
        return max;
    }

    /**
     * 从后往前遍历设置变量max记录最大值，
     * 设置变量result记录结果 Math.max(result,max - prices[i]);
     * 其实就是从后往前寻找最大值的过程，时间复杂度为O(n)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int result = 0;
        int max = 0;
        for (int i = prices.length-1; i >=0; i--) {
            result = Math.max(result,max - prices[i]);
            max = Math.max(max,prices[i]);
        }
        return result;
    }

    /**
     * 从前往后遍历记录最小值（与方法二思路相同）
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int result = 0;
        int min = 10000;
        for (int price : prices) {
            result = Math.max(result, price - min);
            min = Math.min(min, price);
        }
        return result;
    }

}
