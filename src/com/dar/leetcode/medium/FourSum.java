package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 四数之和 https://leetcode.cn/problems/4sum/
 * @create :2022-10-20 13:50:00
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums = {-2,-1,0,0,1,2};
        List<List<Integer>> list = new FourSum().fourSum(nums,0);
        for (List<Integer> integerList : list) {
            System.out.println(integerList);
        }
    }

    /**
     * 与前面的三数之和类似，还是用双指针来解决，
     * 不过这次需要在外层套两层for循环
     *
     * 在确定两个数的基础上去使用双指针寻找另外合适的两个数才能保证遍历完整，即不存在遗漏的组合
     * 每层循环都要注意重重，保证结果中没有重复的数据
     * 进入循环后可以优先判断一些固定条件，如最大和是否小于target，最小和是否大于target来减少一些不必要的循环
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        Arrays.sort(nums);//从小到大排序
        for (int i = 0; i < nums.length-3; i++) {//第一层循环，确定第一个数
            if((long)nums[length-1]+nums[length-2]+nums[length-3]+nums[length-4]<target){//最大值小于target，后面已经不可能了
                break;
            }
            if ((long) nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {//当前最小值大于target，后面已经不可能了
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {//当前最大值小于target
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]){//去重
                continue;
            }
            for (int j = i+1; j < length-2 ; j++) {//第二层循环，确定第二个数
                if ((long) nums[j] + nums[i] + nums[length - 2] + nums[length - 1] < target) {//当前最大值小于target
                    continue;
                }
                if ((long) nums[j] + nums[i] + nums[j+1] + nums[j+2] > target) {//当前最小值大于target，退出循环
                    break;
                }
                if(j>i+1&&nums[j]==nums[j-1]){//去重
                    continue;
                }
                int left = j+1;//左指针
                int right = length-1;//右指针
                while (left<right){//防止越界
                    long num = (long) nums[i] + nums[j] + nums[left] + nums[right];//四数和（需要转成long型 防止越界）
                    if(num>target){
                        while (left<right&&nums[right-1]==nums[right]){//去重
                            right--;
                        }
                        right--;//右指针左移
                    } else if(num<target){
                        while (left<right&&nums[left+1]==nums[left]){//去重
                            left++;
                        }
                        left++;//左指针右移
                    } else {//四数和符合要求
                        List<Integer> list1 = new ArrayList<>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[left]);
                        list1.add(nums[right]);
                        list.add(list1);//添加集合
                        //去重 移动指针
                        while (left<right&&nums[right-1]==nums[right]){
                            right--;
                        }
                        right--;
                        while (left<right&&nums[left+1]==nums[left]){
                            left++;
                        }
                        left++;
                    }
                }
            }
        }
        return list;
    }
}
