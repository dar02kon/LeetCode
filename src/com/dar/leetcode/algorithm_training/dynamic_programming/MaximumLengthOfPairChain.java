package com.dar.leetcode.algorithm_training.dynamic_programming;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 646. 最长数对链 https://leetcode.cn/problems/maximum-length-of-pair-chain/
 * @create :2023-02-09 10:38:00
 */
public class MaximumLengthOfPairChain {

    public static void main(String[] args) {

    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        int max = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] tail = new int[pairs.length];
        int size = 0;
        for (int[] pair : pairs) {
            int index = binarySearch(tail, size, pair[0]);
            if (index < size)
                tail[index] = Math.min(pair[1], tail[index]);
            else
                tail[index] = pair[1];
            if (index == size) {
                size++;
            }
        }
        return size;
    }


    private int binarySearch(int[] tail, int right, int key) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tail[mid] == key) {
                return mid;
            }
            if (tail[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
