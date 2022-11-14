package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 11. 旋转数组的最小数字 https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/?favorite=xb9nqhhg
 * @create :2022-11-14 13:11:00
 */
public class RotateTheSmallestNumberOfAnArray {

    public static void main(String[] args) {

    }

    /**
     * 二分查找
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int left = 0;//左指针
        int right = numbers.length - 1;//右指针
        while (left < right) {
            int mid = left + (right - left) / 2;//中间点
            if (numbers[mid] < numbers[right]) {
                right = mid;//mid可能就是需要找的位置
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;//这个mid肯定不是要找的位置，因为numbers[mid]>numbers[right]
            } else {
                right--;//左移一位继续试探
            }
        }
        return numbers[left];//返回numbers[right]也可以
    }
}
