package com.dar.leetcode.simple;

import java.text.SimpleDateFormat;

/**
 * @author :wx
 * @description :最长公共前缀 https://leetcode.cn/problems/longest-common-prefix/
 * @create :2022-09-19 21:05:00
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flow","flow","flow"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }

    /**
     * 简单粗暴：
     * 先找出最多字符串的长度，根据这个长度进行遍历
     * 选取一个字符串为基准，若出现其它字符串与该字符串对应位不相同
     * 则返回stringBuffer.toString()，否则stringBuffer.append(strs[0].charAt(i))
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        int num = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            num = Math.min(num, strs[i].length());
        }
        StringBuilder stringBuffer = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            for (int j = 1; j < strs.length; j++) {
                if(strs[j].charAt(i)!=strs[0].charAt(i)){
                    return stringBuffer.toString();
                }
            }
            stringBuffer.append(strs[0].charAt(i));
        }
        return stringBuffer.toString();
    }
}
