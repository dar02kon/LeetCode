package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 56 - II. 数组中数字出现的次数 II https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-27 19:38:00
 */
public class NumberOfOccurrencesOfANumberInTheArrayII {

    public static void main(String[] args) {

    }

    public int singleNumber(int[] nums) {
        int two=0,one=0;
        for (int num : nums) {
            one = (one^num)&(~two);
            two = (two^num)&(~one);
        }
        return one;
    }
}
