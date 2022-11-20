package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 17. 打印从1到最大的n位数 https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-20 09:57:00
 */
public class PrintFromOneToTheLargestNumberOfNDigits {
    public static void main(String[] args) {
        int[] ints = new PrintFromOneToTheLargestNumberOfNDigits().printNumbers(1);
        System.out.println(Arrays.toString(ints));
    }

    public int[] printNumbers(int n) {
        int len = (int) Math.pow(10,n);
        int[] result = new int[len-1];
        for (int i = 0; i <= (len-1)/2; i++) {
            result[i]=i+1;
            result[len-2-i]=len-i-1;
        }
        return result;
    }
}
