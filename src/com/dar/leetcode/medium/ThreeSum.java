package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 三数之和 https://leetcode.cn/problems/3sum/
 * @create :2022-10-17 12:28:00
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] num = {-1,-1,1,0};
        List<List<Integer>> list = new ThreeSum().threeSum(num);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }

    /**
     * 双指针
     * <P>
     *     前言：
     *       我一开始写这道题时陷入了一个误区，一开始就觉得要用双指针，于是便定义了左右指针
     *       企图使时间复杂度接近O(n)，但很显然不可能，尤其是在三数之和为0使，无法判断指针移动方向，
     *       归根结底还是因为选定两个数（左右指针）并得出结果后，我们无法判断谁还有价值需要留下来。
     *       但选定一个数后，再去使用双指针去寻找另外两个数（可能有多个）就不会有这种困难
     * </P>
     * 我们需要选定一个数，然后使用双指针去寻找合适的另外两个数（可能存在多个，如2 + -2 + 0 和 2 + -1 + -1）。
     * 需要注意去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length<3){//元素个数小于3直接返回
            return list;
        }
        Arrays.sort(nums);//从小到大排序
        if(nums[0]>0||nums[nums.length-1]<0){//如果最小值大于0或者最大值小于0，则肯定不存在直接返回
            return list;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]>0){//当前最小值大于0，跳出循环（三个数中最小值大于0则和不可能等于0）
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {//去重
                continue;
            }
            int left = i+1;//左指针
            int right = nums.length-1;//右指针
            while (left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum>0){//和大于0说明最大值大了，得减小
                    right--;
                }else if(sum<0){//和小于0说明最小值太小了，得增大
                    left++;
                } else {//等于0
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);//添加元素
                    while (left<right && nums[left]==nums[left+1])//左指针去重
                        left++;
                    while (left<right && nums[right]==nums[right-1])//右指针去重
                        right--;
                    left++;
                    right--;
                }
            }
        }
        return list;//返回
    }
}
