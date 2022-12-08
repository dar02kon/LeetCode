package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author :wx
 * @description : 剑指 Offer 40. 最小的k个数 https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/?favorite=xb9nqhhg
 * @create :2022-12-08 11:13:00
 */
public class TheSmallestKNumber {

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 2};
        int[] leastNumbers = new TheSmallestKNumber().getLeastNumbers(nums, 3);
        System.out.println(Arrays.toString(leastNumbers));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0) { // 排除 0 的情况
            return result;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((num1, num2) -> num2 - num1);
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }
}
