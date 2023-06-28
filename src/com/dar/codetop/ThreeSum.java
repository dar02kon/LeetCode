package com.dar.codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 15. 三数之和 https://leetcode.cn/problems/3sum/description/
 * @create :2023-06-28 21:46:00
 */
public class ThreeSum {

    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 长度小于3，结果为空
        if (nums.length < 3) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        // 最小值大于0或者最大值小于0，结果都为空
        if (nums[nums.length - 1] < 0) {
            return result;
        }
        // 选一个元素为基准，双指针找出另外两个元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            // 双指针寻找元素
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 大于0，移动右指针
                if (sum > 0) {
                    right--;
                } else if (sum < 0) { // 小于0，移动左指针
                    left++;
                } else { // 等于0，需要进行去重
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
