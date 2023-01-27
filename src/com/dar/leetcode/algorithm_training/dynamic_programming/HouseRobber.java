package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 198. 打家劫舍 https://leetcode.cn/problems/house-robber/
 * @create :2023-01-27 09:27:00
 */
public class HouseRobber {

    public static void main(String[] args) {
        int rob = new HouseRobber().rob(new int[]{1, 2, 3, 1});
        System.out.println(rob);
    }

    public int rob(int[] nums) {
        int pre1 = 0, pre2 = 0, pre3 = nums[0];
        int max = pre3;
        for (int i = 1; i < nums.length; i++) {
            int num = Math.max(pre1, pre2) + nums[i];
            pre1 = pre2;
            pre2 = pre3;
            pre3 = num;
            max = Math.max(max, pre3);
        }
        return max;
    }
}
