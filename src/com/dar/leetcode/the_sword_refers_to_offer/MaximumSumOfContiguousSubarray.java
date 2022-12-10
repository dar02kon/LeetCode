package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 42. 连续子数组的最大和 https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/?favorite=xb9nqhhg
 * @create :2022-12-10 09:20:00
 */
public class MaximumSumOfContiguousSubarray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = new MaximumSumOfContiguousSubarray().maxSubArray(nums);
        System.out.println(i);
    }

    public int maxSubArray(int[] nums) {
        int record = 0;
        int max = nums[0];
        for (int num : nums) {
            record = Math.max(record + num, num);
            max = Math.max(max, record);
        }
        return max;
    }
}
