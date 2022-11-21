package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面 https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/description/
 * @create :2022-11-21 13:51:00
 */
public class AdjustTheArrayOrderSoThatTheOddNumbersPrecedeTheEvenOnes {
    public static void main(String[] args) {
        int[] num = {1,2,3,4};
        int[] exchange = new AdjustTheArrayOrderSoThatTheOddNumbersPrecedeTheEvenOnes().exchange(num);
        System.out.println(Arrays.toString(exchange));
    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left<right){
            while (left<right&&nums[left]%2==1){
                left++;
            }
            while (left<right&&nums[right]%2==0){
                right--;
            }
            if(left<right){
                int target = nums[left];
                nums[left] = nums[right];
                nums[right] = target;
                left++;
                right--;
            }
        }
        return nums;
    }

    public int[] exchange2(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length-1;
        for (int num : nums) {
            if(num%2==0){
                result[right--]=num;
            } else {
                result[left++] =num;
            }
        }
        return result;
    }
}
