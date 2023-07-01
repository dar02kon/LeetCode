package com.dar.codetop;

import java.util.Random;

/**
 * @author :wx
 * @description : 912. 排序数组 https://leetcode.cn/problems/sort-an-array/description/
 * @create :2023-07-01 22:13:00
 */
public class SortAnArray {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static final Random RANDOM = new Random();

    /**
     * 三指针快排（将与目标元素值相同的元素都移至中间区域，左边区域均小于，右边区域均大于）
     */
    private void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        // 随机一个目标值
        int random = left + RANDOM.nextInt(right - left + 1);
        // 先移至最左边，防止干扰遍历（非必须）
        swap(nums, random, left);
        // 左指针，负责小于目标值元素的安放
        int leftIndex = left;
        // 右指针，负责大于目标值元素的安放
        int rightIndex = right + 1;
        // 前面交换了，所以从left+1开始遍历
        int i = left + 1;
        while (i < rightIndex) {
            if (nums[i] < nums[left]) { // 值小于目标元素，往左移到
                leftIndex++;
                swap(nums, leftIndex, i);
                i++;
            } else if (nums[i] > nums[left]) { // 值大于目标元素，尝试交换
                // 但不能保证nums[rightIndex]值的大小，
                // 交换后nums[i]可能不符合要求，需要继续判断
                rightIndex--;
                swap(nums, i, rightIndex);
            } else { // 相同则跳过
                i++;
            }
        }
        // 将目标元素移至中间
        swap(nums, leftIndex, left);
        // 继续对左区域进行排序
        quickSort(nums, left, leftIndex - 1);
        // 继续对右区域进行排序
        quickSort(nums, rightIndex, right);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
