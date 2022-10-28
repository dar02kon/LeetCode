package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 在排序数组中查找元素的第一个和最后一个位置 https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @create :2022-10-28 13:55:00
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,4};
        int[] range = new FindFirstAndLastPositionOfElementInSortedArray().searchRange2(nums,4);
        for (int i : range) {
            System.out.println(i);
        }
    }

    /**
     * 二分查找
     * 找到目标值后再从中间往两边进行搜索
     *退出搜索的情况：
     *  双方均越界，一方越界一方不满足条件（不等于目标值），双方均不满足条件（即均不等于目标值）
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==1&&nums[0]==target){//特殊情况处理
            return new int[]{0,0};
        }
        int[] result = {-1,-1};//初始化
        int left = 0,right = nums.length-1;
        //二分查找
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){//找到目标值后，从中间往两边搜素
                left = mid-1;
                right = mid+1;
               while (left>=0||right<=nums.length-1){//双方均越界则退出
                   if(left>=0&&nums[left]==target){
                       left--;
                   }
                   if(right<= nums.length-1&&nums[right]==target){
                       right++;
                   }
                   //退出情况：一方越界一方不满足条件（不等于目标值），双方均不满足条件（即均不等于目标值）
                   if((left>=0&&right<= nums.length-1&&nums[left]!=target&&nums[right]!=target)||(left<0&&right<= nums.length-1&&nums[right]!=target)||(left>=0&&right>nums.length-1&&nums[left]!=target)){
                      break;
                   }
               }
               result[0] = left+1;
               result[1] = right-1;
               break;
            }
            if(nums[mid]<target){
                left = mid + 1;
            }
            if (nums[mid]>target){
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * 两次二分查找
     * 我们需要的左边界是第一个等于目标值的下标，右边界是最后一个等于目标值的下标
     * 我们可用通过寻找第一个>=目标值的下标和第一个大于目标值的下标来推出边界
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int left = search(nums, target, true);//第一个>=target的下标
        int right = search(nums, target, false)-1;//第一个>target的小标（减一后就是右边界）
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target) {//判断左右边界是否合法可用
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    /**
     * 根据flag进行二分查找
     * flag为true，则查找第一个大于或等于目标值的下标
     *     为false，则查找第一个大于目标值的下标
     * @param nums
     * @param target
     * @param flag
     * @return
     */
    public int search(int[] nums,int target,boolean flag){
        int left = 0,right = nums.length-1,result = nums.length;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]>target||(flag&&nums[mid]>=target)){
                right = mid-1;
                result = mid;//记录每一次可能的结果，以最后的为准
            } else {
                left = mid+1;
            }
        }
        return result;
    }
}
