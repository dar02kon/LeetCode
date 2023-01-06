package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 面试题67. 把字符串转换成整数 https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-06 13:51:00
 */
public class StringConversionInteger {

    public static void main(String[] args) {

    }

    public int myAtoi(String s) {
        int i = 0;
        boolean flag = false;//符号判断
        int temp = Integer.MAX_VALUE/10;//用于越界判断
        int result = 0;//结果
        while (i<s.length()&&s.charAt(i)==' '){
            i++;
        }
        if(i==s.length()) return 0;//空串
        if(s.charAt(i)=='-') flag = true;
        if(s.charAt(i)=='+'||s.charAt(i)=='-') i++;
        for (; i < s.length() ; i++) {
            if(s.charAt(i)<'0'||s.charAt(i)>'9'){//无关字符
                break;
            }
            if(result>temp||(result==temp&&s.charAt(i)-'0'>7)){//越界判断
                return flag?Integer.MIN_VALUE:Integer.MAX_VALUE;
            } else {
                result = result*10+(s.charAt(i)-'0');//拼接数字
            }
        }
        return flag?-result:result;
    }
}
