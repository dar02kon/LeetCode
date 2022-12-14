package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 53 - I. 在排序数组中查找数字 I https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-14 18:24:00
 */
public class FindsANumberInASortedArray {

    public static void main(String[] args) {
        int[] nums = {2,4,6,7,8};
        int i = new FindsANumberInASortedArray().search(nums, 5);
        System.out.println(i);
    }

    public int search(int[] nums, int target) {
        int left = searchM(nums, target);
        int right = searchN(nums, target);
        return right-left+1;
    }

    public int searchM(int[] nums, int target){//寻找第一个大于等于target的nums[i]
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]<target){
                left = mid+1;
            } else {
                right = mid - 1 ;
            }
        }
        return left;
    }

    public int searchN(int[] nums, int target){//寻找第一个小于等于target的nums[i]
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]>target){
                right = mid - 1;
            } else {
                left = mid+1 ;
            }
        }
        return right;
    }
}
