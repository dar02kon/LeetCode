package com.dar.codetop;

/**
 * @author :wx
 * @description : 300. 最长递增子序列 https://leetcode.cn/problems/longest-increasing-subsequence/
 * @create :2023-07-24 22:21:00
 */
public class LongestIncreasingSubsequence {
    /**
     * 动态规划
     * dp[i]表示以nums[i]结尾的最长递增子序列的长度
     * dp[i] = Math.max(dp[i],dp[j]+1); j < i
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * tail[i]表示最长递增子序列的长度为i时的最后一个元素，且这个元素是所有选择中最小的
     * 因为最小最有可能与后面再匹配上，因此tail肯定是递增的
     * 思路：
     * 新建数组 tail，用于保存最长上升子序列。
     * 对原序列进行遍历，将每位元素二分插入 tail 中。
     * 如果 tail 中元素都比它小，将它插到最后，序列长度+1
     * 否则，用它覆盖掉比它大的元素中最小的那个。
     * 总之，思想就是让 tail 中存储比较小的元素。这样，tail 未必是真实的最长上升子序列，但长度是对的。
     */
    public int lengthOfLIS2(int[] nums) {
        int[] tail = new int[nums.length];
        int result = 0;
        for (int num : nums) {
            int left = 0;
            int right = result;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tail[mid] < num) left = mid + 1;
                else right = mid;
            }
            tail[left] = num;
            if (right == result) result++;
        }
        return result;
    }
}
