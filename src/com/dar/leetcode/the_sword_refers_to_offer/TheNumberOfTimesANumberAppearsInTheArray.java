package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 56 - I. 数组中数字出现的次数 https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-25 10:08:00
 */
public class TheNumberOfTimesANumberAppearsInTheArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,1,2};
        int[] ints = new TheNumberOfTimesANumberAppearsInTheArray().singleNumbers(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] singleNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        int dividingLine = 1;
        while ((result & dividingLine) == 0) {
            dividingLine <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if((num&dividingLine)==0){
                a^=num;
            } else {
                b^=num;
            }
        }
        return new int[]{a,b};
    }
}
