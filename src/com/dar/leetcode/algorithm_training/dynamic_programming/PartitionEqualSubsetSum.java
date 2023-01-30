package com.dar.leetcode.algorithm_training.dynamic_programming;


/**
 * @author :wx
 * @description : 416. 分割等和子集 https://leetcode.cn/problems/partition-equal-subset-sum/description/
 * @create :2023-01-30 10:02:00
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        boolean b = new PartitionEqualSubsetSum().canPartition(new int[]{1, 2, 5, 2});
        System.out.println(b);
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {// 数组和
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;// 背包大小
        // dp[i][j]表示数字j能否由下标为i以及i之前的数字组成
        boolean[][] dp = new boolean[nums.length][sum+1];
        if (nums[0] <= sum)
            dp[0][nums[0]] = true;// 第一个元素只能表示一个数字
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 递推关系
                // 若j<nums[i],说明下标i对应的元素太大，不能参加表示，
                // 只能用前下标为i-1以及之前的元素来表示
                if (j >= nums[i])
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
            //找到可以提前结束
            if (dp[i][sum]) {
                return true;
            }
        }
        return dp[nums.length - 1][sum];
    }


    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        // dp[i]表示数组中的数字能否组成i
        boolean[] dp = new boolean[sum + 1];
        if (nums[0] <= sum)
            dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            //从后往前
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}
