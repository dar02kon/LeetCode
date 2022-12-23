package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 57. 和为s的两个数字 https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-23 23:04:00
 */
public class TheSumIsTwoNumbersOfS {
    public static void main(String[] args) {

    }

    /**
     * 二分搜索
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i <nums.length; i++) {
            if(nums[i]>=target){
                break;
            }
            result[0]=nums[i];
            if(search(nums, i, target - result[0])){
                result[1] = target-result[0];
                return result;
            }
        }
        return result;
    }

    public boolean search(int[] nums,int left,int target){
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                return true;
            }
            if(nums[mid]>target){
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return false;
    }

    /**
     * 双指针
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left<right){
            int result = nums[left]+nums[right];
            if(result==target){
                return new int[]{nums[left],nums[right]};
            }
            if(result>target){
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
}
