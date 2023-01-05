package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 面试题61. 扑克牌中的顺子 https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-05 10:50:00
 */
public class ACardInAPlayingCard {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 8, 5, 4};
        boolean straight = new ACardInAPlayingCard().isStraight(nums);
        System.out.println(straight);
    }

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {//统计0的个数，最多两个
                count++;
                continue;
            }
            if (nums[i] == nums[i + 1]) {//出现除0外的重复数字
                return false;
            }
            count -= nums[i + 1] - nums[i] - 1;//消耗0
        }
        return count >= 0;
    }
}
