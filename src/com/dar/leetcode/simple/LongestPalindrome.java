package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 最长回文串 https://leetcode.cn/problems/longest-palindrome/
 * @create :2022-11-24 19:33:00
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        int i = new LongestPalindrome().longestPalindrome("abccccdd");
        System.out.println(i);
    }

    /**
     * 记录字符出现的次数
     * 若某个字符出现的次数为偶数，则这些字符肯定可以表示回文串
     * 为奇数则需要先减一变成偶数，最后再判断是否要在中间加一个字符
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] count = new int[128];//ascii 码总共 128 个
        int result = 0;
        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            count[c]++;
        }
        for (int i : count) {
            result += (i>>1<<1);//先除以2再乘以2（对奇偶做统一处理）
        }
        return result==s.length()?result:result+1;//最后需要判断是否需要在中间加一个字符
    }
}
