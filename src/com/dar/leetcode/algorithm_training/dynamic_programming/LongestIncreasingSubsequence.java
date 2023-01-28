package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 300. 最长递增子序列 https://leetcode.cn/problems/longest-increasing-subsequence/
 * @create :2023-01-28 10:02:00
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;//最小为1，自己构成一个子串
            for (int j = 0; j < i; j++) {//选取最长的
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tail = new int[nums.length];
        int size = 0;//记录最长递增子序列的长度
        for (int num : nums) {
            int index = binarySearch(tail, size, num);
            tail[index] = num;
            if (index == size) size++;//已经新加了一个字符
        }
        return size;
    }
    // 二分搜索，若数组元素均大于目标值，返回0，更新下标为0的元素；
    // 均小于目标值，返回size，新加一个元素
    private int binarySearch(int[] tail, int right, int key) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tail[mid] == key) return mid;
            if (tail[mid] > key) right = mid;
            else left = mid + 1;
        }
        return left;
    }

}
