package com.dar.codetop;

/**
 * @author :wx
 * @description : 31. 下一个排列 https://leetcode.cn/problems/next-permutation/description/
 * @create :2023-07-22 18:37:00
 */
public class NextPermutation {
    /**
     * 分三步走：
     * 1、从后往前找到nums[index]>nums[index-1]时index的值（index>0，则说明存在稍微大一点的排序）
     * 2、如果index>0，从后往前找，找到第一个比nums[index-1]值大的元素（index后的元素都是降序的，不能确保都比nums[index-1]小）
     * 找到则与index-1对应的元素交换（最坏就是index与index-1交换）
     * 3、将index-1后的元素进行重排序，升序，使值最小（交换后index-1后的元素依然是降序的，使用双指针即可进行升序排序）
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length - 1;
        int index = len;
        for (; index > 0; index--) {
            if (nums[index] > nums[index - 1]) {
                break;
            }
        }
        if (index > 0) {
            for (int i = len; i >= index; i--) {
                if (nums[i] > nums[index - 1]) {
                    swap(nums, i, index - 1);
                    break;
                }
            }
        }
        while (index < len) {
            swap(nums, index, len);
            index++;
            len--;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
