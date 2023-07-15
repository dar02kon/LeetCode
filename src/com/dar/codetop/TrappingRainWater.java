package com.dar.codetop;

import java.util.Stack;

/**
 * @author :wx
 * @description : 42. 接雨水 https://leetcode.cn/problems/trapping-rain-water/description/
 * @create :2023-07-15 19:18:00
 */
public class TrappingRainWater {
    /**
     * 用栈求解
     */
    public int trap(int[] height) {
        int current = 0;
        int sum = 0;
        // 保证栈中柱子对应高度递减
        Stack<Integer> stack = new Stack<>();
        while (current < height.length) {
            // 当前高度大于栈顶高度，有水可积
            while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                // 积水区
                Integer minHeight = stack.pop();
                // 左边没有高柱子，积不了水
                if (stack.isEmpty()) {
                    break;
                }
                Integer left = stack.peek();
                // 计算积水区域距离
                int distance = current - left - 1;
                // 计算当前积水区域高度
                int h = Math.min(height[left], height[current]) - height[minHeight];
                // 加水
                sum += distance * h;
            }
            stack.push(current);
            current++;
        }
        return sum;
    }

    /**
     * 动态规划
     * 如果直到当前区域左边最高的柱子与右边最高的柱子，就能求出当前区域的积水
     */
    public int trap2(int[] height) {
        // left[i]表示i左边最高的柱子
        int[] left = new int[height.length];
        // right[i]表示i右边最高的柱子
        int[] right = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            left[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            right[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        // 假设i为积水区域，求可以积的水
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(left[i], right[i]);
            if (min > height[i]) {
                max += min - height[i];
            }
        }
        return max;
    }

    /**
     * 双指针，left[i]与right[i]只有遍历到i时才会用到，且只需要最小的那个
     * 可以使用双指针来减少存储空间
     */
    public int trap3(int[] height) {
        // 记录当前左边柱子最大高度
        int left_max = 0;
        // 记录当前右边柱子最大高度
        int right_max = 0;
        // 左指针，从1开始，第一个柱子不可能有积水
        int left = 1;
        // 右指针，从倒数第二个开始，最后一个柱子不可能有积水
        int right = height.length - 2;
        // 积水量
        int sum = 0;
        while (left <= right) {
            // 左右最小值取左
            if (height[left - 1] < height[right + 1]) {
                // 更新当前左边柱子最大高度
                left_max = Math.max(left_max, height[left - 1]);
                // 满足积水条件，即中间有落差
                if (left_max > height[left]) {
                    sum += left_max - height[left];
                }
                left++;
            } else { // 左右最小值取右
                // 更新当前右边柱子最大高度
                right_max = Math.max(right_max, height[right + 1]);
                if (right_max > height[right]) {
                    // 满足积水条件，即中间有落差
                    sum += right_max - height[right];
                }
                right--;
            }
        }
        return sum;
    }
}