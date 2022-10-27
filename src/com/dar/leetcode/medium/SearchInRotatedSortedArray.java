package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 搜索旋转排序数组 https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * @create :2022-10-27 14:44:00
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,2};
        int search = new SearchInRotatedSortedArray().search(nums, 1);
        System.out.println(search);
    }

    /**
     * 二分查找
     * 对于数组[4,5,6,7,1,2,3]，
     * 我们可以将该数组分为两块，每一块都是递增序列，可以进行二分查找
     * 每一次我们取”中间值“，首先判断mid位于哪一块
     * 如果nums[mid] < nums[0]则mid位于右边那一块递增序列，
     * 如果满足nums[mid]<=target<=nums[nums.length-1]（如果存在目标值则一定位于这个范围），则左指针移动，
     * 否则右指针移动（target肯定位于mid左边）
     * 位于左边那一块序列同理
     *
     * 循环结束如果没有找到返回-1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length==0){
            return -1;
        }
        if(nums.length==1){
            return nums[0]==target?0:-1;
        }
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid = (left+right);
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]<nums[0]){//靠近右边
                if(nums[mid]<=target&&target<=nums[nums.length-1]){
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            } else {//靠近左边
                if(nums[mid]>=target&&target>=nums[0]){
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
        }
        return -1;
    }
}
