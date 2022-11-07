package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author :wx
 * @description : 剑指 Offer 03. 数组中重复的数字 https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-07 15:58:00
 */
public class ADuplicateNumberInAnArray {

    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 1, 1, 0};
        int repeatNumber = new ADuplicateNumberInAnArray().findRepeatNumber3(nums);
        System.out.println(repeatNumber);
    }

    /**
     * 排序后查找
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 使用hash表来存储已经出现的数字辅助判断
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>(nums.length - 1);
        for (int num : nums) {
            if (hashSet.contains(num)) {
                return num;
            }
            hashSet.add(num);
        }
        return -1;
    }

    /**
     * 充分利用题目所给条件：在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 调整数组至nuns[i] = i，如果出现nums[nums[i]] = nums[i]，则说明有重复
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {//该位置已经调整好
                i++;
                continue;
            }
            if (nums[i] == nums[nums[i]]) {//出现冲突
                return nums[i];
            } else {//交换（不断交换至nums[i] == i）
                int record = nums[i];
                nums[i] = nums[record];
                nums[record] = record;
            }
        }
        return -1;
    }


}
