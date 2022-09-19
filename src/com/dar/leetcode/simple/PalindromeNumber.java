package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 9. 回文数 https://leetcode.cn/problems/palindrome-number/
 * @create :2022-09-19 19:16:00
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome2(121));
    }

    /**
     * 最普通的写法，先将数字转字符串，for循环遍历，首尾相比不相同则直接返回false
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if(x<0)
            return false;
        String s = x + "";
        int len = s.length();
        for (int i = 0; i < len/2 ; i++) {
            if(!(s.charAt(i)==s.charAt(len-1-i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 参考别人的思路 ：
     * 如果x（整数）小于0或者能被十整除都不会是回文数（整数首位不为0）
     * 创建变量reversedNumber存储反转后的数字
     * reversedNumber = reversedNumber * 10 + x % 10；
     * x /= 10;至reversedNumber不小于x
     * x如果为奇数reversedNumber会比x多一位
     */
    public boolean isPalindrome2(int x) {
        if(x<0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int reversedNumber = 0;
        while (reversedNumber<x){
            reversedNumber = reversedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == reversedNumber || x == reversedNumber / 10;
    }


}
