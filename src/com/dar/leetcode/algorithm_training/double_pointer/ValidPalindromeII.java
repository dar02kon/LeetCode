package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 680. 验证回文串 II https://leetcode.cn/problems/valid-palindrome-ii/
 * @create :2023-01-10 10:32:00
 */
public class ValidPalindromeII {

    public static void main(String[] args) {

    }

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }
            return check(s, left + 1, right) || check(s, left, right - 1);
        }
        return true;
    }

    public boolean check(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }
            return false;
        }
        return true;
    }
}
