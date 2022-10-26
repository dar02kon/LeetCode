package com.dar.leetcode.medium;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 下一个排列 https://leetcode.cn/problems/next-permutation/
 * @create :2022-10-26 10:24:00
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {3,2,1};
        new NextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 下一个排列需要比上一个排列大一些（降序除外），可以大但只能大一点
     * 我们需要找到一个较小值，再在这最小值的基础上去找一个较大值
     * 较小的数尽可能靠右，较大的数尽可能小
     * 如果我们人工去排列，我们会先确定首部的排列再分情况去确定后面的排列，对于1，2，3的从小到大排列
     * 我们会先确定1在前面的情况，然后是2在前面的情况，最后是3在前面的情况，每中情况内部也是这样处理（递归）
     *      <p>
     *          1 2 3
     *          1 3 2
     *          2 1 3
     *          2 3 1
     *          3 1 2
     *          3 2 1
     *      </p>
     * 模拟这种思路，要想让这个排列变大就需要将小的值放在后面，大的值放在前面
     * 我们从后往前找一个较小值 出现nums[i-1] < nums[i]，nums[i-1]就是我们要找的较小值（因为它比较小还出现在前面）
     * 在这个较小值的基础上，从右往左寻找较大值（比较小值大就可以），出现nums[j] > nums[i - 1]，nums[j]就是我们要找的较大值
     * 将这两个位置互换，仅仅互换还不够，还需要将i及后面的元素进行升序排序
     *      <p>
     *          对与 2 3 1，交换后是 3 2 1 需要将2 1按升序排序即3 1 2（因为它换“头”了）
     *          首部换了，后面就需要重新排序
     *          同时我们会发现需要排序的元素是按降序排序的，我们只需要左右交换就可以完成排序
     *          （前面的两次遍历+交换 其实就可以确定后面的元素是降序的）
     *      </p>
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length-1;
        int i = length;
        for (; i >0 ; i--) {
            if(nums[i]>nums[i-1]){
                break;
            }
        }
        int j = length;
        if(i>0) {
            for (; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    break;
                }
            }
            swap(nums,i-1,j);
        }
        while (i<length){
            swap(nums,i,length);
            i++;
            length--;
        }
    }
    public void swap(int[] nums, int left, int right){
        int num = nums[left];
        nums[left] = nums[right];
        nums[right] = num;
    }
}
