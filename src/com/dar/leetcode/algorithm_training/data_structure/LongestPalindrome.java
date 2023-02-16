package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 409. 最长回文串 https://leetcode.cn/problems/longest-palindrome/description/
 * @create :2023-02-16 14:05:00
 */
public class LongestPalindrome {

    public static void main(String[] args) {

    }

    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (int i = 0; i <s.length() ; i++) {
            count[s.charAt(i)]++;
        }
        int sum = 0;
        for (int num : count) {
            sum+=num/2*2;
        }
        return sum<s.length()?sum+1:sum;
    }
}
