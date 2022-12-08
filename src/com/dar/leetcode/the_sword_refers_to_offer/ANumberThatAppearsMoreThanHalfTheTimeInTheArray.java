package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 39. 数组中出现次数超过一半的数字 https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/?favorite=xb9nqhhg
 * @create :2022-12-08 10:45:00
 */
public class ANumberThatAppearsMoreThanHalfTheTimeInTheArray {

    public static void main(String[] args) {

    }

    /**
     * Boyer-Moore 投票算法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int result = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                result = num;
            }
            count = result == num ? count + 1 : count - 1;
        }
        return result;
    }
}
