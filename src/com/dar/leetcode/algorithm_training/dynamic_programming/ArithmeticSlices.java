package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 413. 等差数列划分 https://leetcode.cn/problems/arithmetic-slices/description/
 * @create :2023-02-06 10:52:00
 */
public class ArithmeticSlices {

    public static void main(String[] args) {

    }

    public int numberOfArithmeticSlices(int[] nums) {
        int len = 2;// 记录组成等差数列的元素个数
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){// 满足等差数列的条件
                len++;
                sum+=len-2;
            } else {
                len = 2;
            }
        }
        return sum;
    }
    public int numberOfArithmeticSlices2(int[] nums) {
        int[] dp = new int[nums.length];
        int sum = 0;
        for (int i = 2; i <nums.length ; i++) {
            if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){
                dp[i] = dp[i-1]+1;
                sum+=dp[i];
            }
        }
        return sum;
    }

}
