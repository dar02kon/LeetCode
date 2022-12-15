package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 53 - II. 0～n-1中缺失的数字 https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-15 21:15:00
 */
public class TheNumberIsMissingFrom0ToNMinus1 {

    public static void main(String[] args) {

    }

    public int missingNumber(int[] nums) {
        int i = 0;
        for (; i < nums.length; i++) {
            if(i!=nums[i]){
                return i;
            }
        }
        return i;
    }

    public int missingNumber2(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==mid){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }

}
