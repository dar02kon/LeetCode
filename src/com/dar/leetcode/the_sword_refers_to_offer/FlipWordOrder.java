package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 58 - I. 翻转单词顺序 https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/?favorite=xb9nqhhg
 * @create :2022-12-29 10:29:00
 */
public class FlipWordOrder {

    public static void main(String[] args) {
        String s = new FlipWordOrder().reverseWords("the sky is blue");
        System.out.println(s);
    }

    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();//保存结果
        StringBuilder record = new StringBuilder();//用于拼接字符串
        int left = 0, right = s.length() - 1;
        //去掉字符串前面的空格
        while (left < s.length() && s.charAt(left) == ' ') {
            left++;
        }
        //去掉字符串后面的空格
        while (right >= 0 && s.charAt(right) == ' ') {
            right--;
        }
        while (left <= right) {
            char c = s.charAt(right);
            if (c == ' ') {//以空格为分割线
                result.append(record).append(' ');
                record.delete(0, record.length());
                while (right >= 0 && s.charAt(right) == ' ') {//去掉剩余空格
                    right--;
                }
            } else {
                record.insert(0, c);
                right--;
            }
        }
        result.append(record);
        return result.toString();
    }

    public String reverseWords2(String s) {
        String[] strs = s.split(" ");//分割字符串
        StringBuilder result = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) continue;//处理连续空格产生的空串
            result.append(strs[i]).append(" ");
        }
        if (result.length() - 1 >= 0) result.delete(result.length() - 1, result.length());//去掉末尾空格
        return result.toString();
    }

}
