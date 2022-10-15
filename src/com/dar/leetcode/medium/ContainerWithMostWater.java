package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 盛最多水的容器 https://leetcode.cn/problems/container-with-most-water/
 * @create :2022-10-15 12:30:00
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(new ContainerWithMostWater().maxArea(nums));
    }

    /**
     * 双指针
     * 取两个变量记录数组的起始与终点位置
     * 计算面积与max进行比较
     * 移动对应数组值小的那个指针，直到两指针重合
     * <P>
     *     为什么要移动对应数组值小的指针？
     *      假如数组height为[1,8,6,2,5,4,8,3,7],left指向height[0]，right指向height[8](即最后一位)，
     *      计算面积后，你会怎么去移动指针呢？
     *          如果移动对应数组值大的指针（如right），则不能保证最大面积能够取到，面积由高度与距离决定，
     *          移动对应数组值大的指针在减小距离的同时不能保证下一个值比原来的大，极端情况下如：[1,2,3,4,5,6,7]
     *          移动对应数组值小的指针我们虽然不能保证下一个值比原来的大，但能保证最大值在前面出现过
     * </P>
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while (left!=right){
            max = Math.max(max,(right-left)*Math.min(height[left],height[right]));
            if(height[left]<height[right]){
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
