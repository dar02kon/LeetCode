package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 44. 数字序列中某一位的数字 https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/description/
 * @create :2022-12-16 16:01:00
 */
public class ADigitInASequenceOfDigits {

    public static void main(String[] args) {

    }

    public int findNthDigit(int n) {
        int digits = 1;
        long num = 1;
        long count = 9;
        while (n>count){
            n-=count;
            digits++;
            num*=10;
            count = digits*9*num;
        }
        long result = num+(n-1)/digits;
        return Long.toString(result).charAt((n - 1) % digits) - '0';
    }
}
