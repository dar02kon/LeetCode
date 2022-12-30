package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.*;

/**
 * @author :wx
 * @description : 剑指 Offer 59 - I. 滑动窗口的最大值 https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-30 11:03:00
 */
public class TheMaximumValueOfTheSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints = new TheMaximumValueOfTheSlidingWindow().maxSlidingWindow3(nums, 3);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 大顶堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        result[0] = priorityQueue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            priorityQueue.add(new int[]{nums[i], i});
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            result[i - k + 1] = priorityQueue.peek()[0];
        }
        return result;
    }

    /**
     * 双端队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        result[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            result[i - k + 1] = nums[deque.peekFirst()];
        }
        return result;
    }

    /**
     * 分块处理
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i % k == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = Math.max(nums[i], prefix[i - 1]);
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1 || (i + 1) % k == 0) {
                suffix[i] = nums[i];
            } else {
                suffix[i] = Math.max(nums[i], suffix[i + 1]);
            }
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = Math.max(prefix[i + k - 1], suffix[i]);
        }
        return result;
    }
}
