package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description :  回文子串 https://leetcode.cn/problems/palindromic-substrings/description/
 * @create :2022-11-24 20:24:00
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        int i = new PalindromicSubstrings().countSubstrings("abc");
        System.out.println(i);
    }

    int result = 0;

    /**
     * 中心拓展
     * 选择一个字符后向两边拓展
     * 拓展分为奇数个与偶数个两种情况
     * 拓展的过程中始终保持对称的状态，不对称则直接退出拓展
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i);//奇数个
            count(s, i, i + 1);//偶数个 count(s, i-1, i);两种写法都可以
        }
        return result;
    }

    public void count(String s, int left, int right) {//拓展
        //不越界且加入的字符相同则继续拓展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            result++;//计数
        }
    }

    /**
     * 动态规划
     * 使用数组dp[i][j]来表示从下标i到j组成的字符串是否为回文串
     * 当i与j对应的字符相同，并且从i+1到j-1组成的字符串是回文串时，dp[i][j]为true，
     * 相当于在回文串的两边再加上相同的字符，肯定还是回文串
     * 判断从i+1到j-1组成的字符串是否为回文串时不能直接根据dp[i+1][j-1]进行判断，需要考虑 a，aa等特殊情况
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;
        for (int j = 0; j < s.length() ; j++) {
            for (int i = 0; i <= j; i++) {//判断从i到j组成的字符串,i<=j
                if(s.charAt(i)==s.charAt(j)&&(j-i<2||dp[i+1][j-1])){//新添的字符相同并且添加前的字符串为回文串
                    dp[i][j]=true;
                    result++;
                }
            }
        }
        return result;
    }
}
