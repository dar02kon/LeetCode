package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 只出现一次的数字 https://leetcode.cn/problems/single-number/
 * @create :2022-10-01 15:08:00
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(new SingleNumber().singleNumber(nums));
    }

    /**
     * 偏向于暴力的解法
     * 两重循环，当发现有值相同时即nums[i]==nums[j] 将nums[i+1]与nums[j]值互换
     * 当一重循环下来，没有值发生互换则nums[i]就是要找的那个值
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = i+1; j < nums.length ; j++) {
                if(nums[i]==nums[j]){
                    flag = false;
                    int num = nums[i+1];
                    nums[i+1] = nums[j];
                    nums[j]=num;
                    i++;
                    break;
                }
            }
            if(flag){
                return nums[i];
            }
        }
        return nums[0];
    }

    /**
     * 使用位运算 异或 (这真没想到。。。。。。)
     * 任何数和0做异或运算，结果仍然是原来的数
     * 任何数和其自身做异或运算，结果是0
     * 异或运算满足交换律和结合律
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
