package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 字符串转换整数 (atoi) https://leetcode.cn/problems/string-to-integer-atoi/
 * @create :2022-10-14 14:38:00
 */
public class StringToIntegerAtoi {

    public static void main(String[] args) {
        System.out.println(new StringToIntegerAtoi().myAtoi2("2147483646"));
    }

    /**
     * 常规思路
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int i = 0;
        for (; i < s.length(); i++) {//去掉前置空格
            if(s.charAt(i)!=' '){
                break;
            }
        }
        if(i>=s.length()){
            return 0;
        }
        int result = 0;
        boolean flag = true;
        int temp;
        if(s.charAt(i)=='-'){//处理符号位（如果有的话）
            flag = false;
            i++;
        } else if(s.charAt(i)=='+'){
            i++;
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if(result>Integer.MAX_VALUE/10&&flag){//越界判断
                    return Integer.MAX_VALUE;
                } else if(-result<Integer.MIN_VALUE/10){
                    return Integer.MIN_VALUE;
                }
                temp = result*10 + s.charAt(i)-'0';
                if(temp<result){//越界判断 2147483648
                    return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
                } else {
                    result = temp;
                }
            } else {
                break;
            }
        }
        return flag?result:-result;
    }

    /**
     * 把越界判断放一起
     * @param s
     * @return
     */
    public int myAtoi2(String s) {
        int i = 0;
        for (; i < s.length(); i++) {//去掉前置空格
            if(s.charAt(i)!=' '){
                break;
            }
        }
        if(i>=s.length()){
            return 0;
        }
        int result = 0;
        boolean flag = true;
        if(s.charAt(i)=='-'){//处理符号位（如果有的话）
            flag = false;
            i++;
        } else if(s.charAt(i)=='+'){
            i++;
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if((result>Integer.MAX_VALUE/10&&flag) || (flag&&result==Integer.MAX_VALUE/10&&(s.charAt(i)-'0')>Integer.MAX_VALUE%10)){//越界判断
                    return Integer.MAX_VALUE;
                } else if((-result<Integer.MIN_VALUE/10)||(-result ==Integer.MIN_VALUE / 10 && -(s.charAt(i)-'0') < Integer.MIN_VALUE % 10)){
                    return Integer.MIN_VALUE;
                }
                result =  result*10 + s.charAt(i)-'0';
            } else {
                break;
            }
        }
        return flag?result:-result;
    }

}
