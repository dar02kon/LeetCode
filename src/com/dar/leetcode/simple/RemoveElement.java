package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description :移除元素 https://leetcode.cn/problems/remove-element/
 * @create :2022-09-21 20:46:00
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {1,3,2,2};
        System.out.println(new RemoveElement().removeElement2(nums,2));
    }

    /**
     * 设置两个变量（指针）一个记录更新后数组的位置，一个记录遍历的位置
     * 如果nums[end]!=val且start与end不相邻，则将nums[end]赋值给start所指的下一个位置，start后移一位
     * 每次循环都需要end++
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if(nums.length==1&&nums[0]==val){
            return 0;
        }
        int start = -1;
        int end = 0;
        while (end< nums.length){
            if(nums[end]!=val){
                if(start+1!=end){
                    nums[++start] = nums[end];
                } else {
                    start++;
                }
            }
            end++;
        }
        return start+1;
    }

    /**
     * 优化思路：因为题目对元素顺序没有要求，所以可以让两个指针往中间靠拢
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        if(nums.length==1&&nums[0]==val){
            return 0;
        }
        int start = -1;//数组中所有位置都需要删除时返回0
        int end = nums.length-1;
        while (start<end){
            if(nums[start+1]==val){
                if(nums[end]!=val){
                    nums[++start] = nums[end];
                }
                end--;
            } else {
                start ++;
            }
        }
        return start+1;
    }

}
