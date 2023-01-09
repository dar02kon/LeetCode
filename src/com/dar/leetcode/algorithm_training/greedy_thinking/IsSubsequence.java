package com.dar.leetcode.algorithm_training.greedy_thinking;

/**
 * @author :wx
 * @description : 392. 判断子序列 https://leetcode.cn/problems/is-subsequence/
 * @create :2023-01-09 10:07:00
 */
public class IsSubsequence {

    public static void main(String[] args) {

    }

    public boolean isSubsequence(String s, String t) {
        int i = 0,j=0;
        while (i<s.length()){
            j = t.indexOf(s.charAt(i),j);//在前一个字符出现的位置往后找
            if(j==-1) return false;//找不到，则不是子序列
            i++;
            j++;
        }
        return true;
    }
}
