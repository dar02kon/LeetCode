package com.dar.leetcode.medium;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 最接近的三数之和 https://leetcode.cn/problems/3sum-closest/
 * @create :2022-10-18 10:12:00
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {4,0,5,-5,3,3,0,-4,-5};
        System.out.println(new ThreeSumClosest().threeSumClosest(nums,-2));
    }

    /**
     * 双指针
     * 因为是三数之和，所以我们需要先确定一个数，再使用双指针去寻找另外两个数
     * 在移动指针的时候我们可能有这样一种想法：
     *      num-target从大于0到小于0（或者从小于0到大于0），最接近target的值肯定位于这个临界之间，
     *      那么是否可以在出现反转时判断是小于target接近target还是大于target接近target，然后直接返回最接近的那个num呢？
     *      如果num值是按顺序进行叠加确实可以，但我们使用双指针时，无法确保num的值是按大小顺序出现的，
     *      我们只知道num值大了就通过移动右指针来减小，num值小了就通过移动左指针来增大，无法保证num的大小顺序（移动指针发生变化时），
     *      例如数组{-5，-5，-4，0，0，3，3，4，5}，目标值为-2
     * 同时可以利用去重去优化算法
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];//初始化
        for (int i = 0; i < nums.length; i++) {
            if(i>0&&nums[i]==nums[i-1]){
                continue;//去重
            }
            int left = i+1;//左指针
            int right = nums.length-1;//右指针
            while (left<right){//循环条件
                int num = nums[i]+nums[left]+nums[right];//三数之和
                result = Math.abs(num-target)<Math.abs(result-target)?num:result;//选取最接近target的值
                if(num-target>0){//差大于0说明num值大了一点，通过移动右指针来减小num的值
                    while (left<right&&nums[right-1]==nums[right]){
                        right--;//去重
                    }
                    right--;
                } else if(num-target<0){//差小于0说明num值小了一点，通过移动左指针来增大num的值
                    while (left<right&&nums[left+1]==nums[left]){
                        left++;//去重
                    }
                    left++;
                } else {//若num值与target值相同则直接返回
                    return result;
                }
            }
        }
        return result;
    }
}
