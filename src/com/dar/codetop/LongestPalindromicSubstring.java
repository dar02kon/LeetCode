package com.dar.codetop;

/**
 * @author :wx
 * @description : 5. 最长回文子串 https://leetcode.cn/problems/longest-palindromic-substring/description/
 * @create :2023-07-06 21:39:00
 */
public class LongestPalindromicSubstring {
    /**
     * 中心扩展
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            // ABA
            int len1 = explore(s, i, i);
            //AABB
            int len2 = explore(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 根据偏移量计算下标
            if (len > right - left) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    /**
     * 向两边扩展，判断是否为回文串
     */
    private int explore(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
