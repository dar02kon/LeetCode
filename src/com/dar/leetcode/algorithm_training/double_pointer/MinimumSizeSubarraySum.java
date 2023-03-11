package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 209. 长度最小的子数组 https://leetcode.cn/problems/minimum-size-subarray-sum/description/
 * @create :2023-03-11 11:04:00
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int len = new MinimumSizeSubarraySum().minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1});
        System.out.println(len);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (left <= right && right < nums.length) {
            sum += nums[right];
            while (sum >= target && left <= right) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;

        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
