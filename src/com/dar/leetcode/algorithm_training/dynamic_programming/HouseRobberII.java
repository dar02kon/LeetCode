package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 213. 打家劫舍 II https://leetcode.cn/problems/house-robber-ii/description/
 * @create :2023-01-27 10:02:00
 */
public class HouseRobberII {

    public static void main(String[] args) {

    }

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    public int rob(int[] nums, int start, int end) {
        int pre1 = 0, pre2 = 0, pre3 = nums[start];
        int max = pre3;
        for (int i = start + 1; i < end; i++) {
            int num = Math.max(pre1, pre2) + nums[i];
            pre1 = pre2;
            pre2 = pre3;
            pre3 = num;
            max = Math.max(max, pre3);
        }
        return max;
    }
}
