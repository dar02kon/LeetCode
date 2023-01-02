package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 66. 构建乘积数组 https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-02 21:06:00
 */
public class BuildProductArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] ints = new BuildProductArray().constructArr2(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        if (a.length == 0) {
            return result;
        }
        int[] left = new int[a.length];
        left[0] = 1;
        int[] right = new int[a.length];
        right[a.length - 1] = 1;
        //记录从左到右的乘积
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        //记录从右到左的乘积
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        for (int i = 0; i < a.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public int[] constructArr2(int[] a) {
        int[] result = new int[a.length];
        if (a.length == 0) {
            return result;
        }
        result[a.length - 1] = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            result[i] = result[i + 1] * a[i + 1];
        }
        int left = 1;
        for (int i = 0; i < a.length; i++) {
            result[i] = left * result[i];
            left *= a[i];
        }
        return result;
    }
}
