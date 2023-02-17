package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 647. 回文子串 https://leetcode.cn/problems/palindromic-substrings/
 * @create :2023-02-17 14:40:00
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {

    }

    int result = 0;
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            statistics(s,i,i);
            statistics(s,i-1,i);
        }
        return result;
    }

    private void statistics(String s, int left, int right) {
        while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
            result++;
        }
    }
}
