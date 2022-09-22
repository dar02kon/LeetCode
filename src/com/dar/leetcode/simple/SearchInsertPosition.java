package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 搜索插入位置 https://leetcode.cn/problems/search-insert-position/
 * @create :2022-09-22 19:02:00
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(new SearchInsertPosition().searchInsert(nums,1));
    }

    /**
     * 虚假的二分查找，比较中间位置的值
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return target<=nums[0] ? 0 : 1;
        }
        int start = 0;
        int end = nums.length-1;
        if(target<nums[start]){
            return 0;
        }
        if (target>nums[end]){
            return end+1;
        }
        int med;
        while (start<end){
//             med = (end+start)/2;
             med = start + (end-start)/2;
            if(target==nums[med]){
                return med;
            } else if(target > nums[med]){
                if(start+1==end){
                    return end;
                }
                start = med;
            } else if(target < nums[med]) {
                if(start+1==end){
                    return end;
                }
                end = med;
            }
        }
        return end;
    }

    /**
     * 标准的二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
//            int mid = (r + l) / 2;
            int mid = l + (r - l) / 2;
            if (nums[mid] < target){
                l = mid + 1;
            } else if(nums[mid] == target){
                return mid;
            } else
                r = mid - 1;
        }
        return l;
    }
}
