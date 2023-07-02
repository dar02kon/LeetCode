package com.dar.codetop;

/**
 * @author :wx
 * @description : 33. 搜索旋转排序数组 https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 * @create :2023-07-02 20:56:00
 */
public class SearchInRotatedSortedArray {
    /**
     * 二分查找
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[0]) { // mid在右边有序区域
                // 只有 nums[mid]<=target<= nums[nums.length-1]时才需要移到左指针
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // mid在左边有序区域
                // 只有 nums[0]<=target<= nums[mid]时才需要移到右指针
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
