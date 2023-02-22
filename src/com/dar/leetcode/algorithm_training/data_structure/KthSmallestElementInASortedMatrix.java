package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author :wx
 * @description : 378. 有序矩阵中第 K 小的元素 https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * @create :2023-02-22 10:07:00
 */
public class KthSmallestElementInASortedMatrix {

    public static void main(String[] args) {

    }

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int left = matrix[0][0], right = matrix[m - 1][n - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int[] ints : matrix) {
                for (int j = 0; j < n && ints[j] <= mid; j++) {
                    count++;
                }
            }
            if (count < k) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.offer(new Tuple(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple tuple = priorityQueue.poll();
            assert tuple != null;
            if (tuple.x == m - 1) continue;
            priorityQueue.offer(new Tuple(tuple.x + 1, tuple.y, matrix[tuple.x + 1][tuple.y]));
        }
        return Objects.requireNonNull(priorityQueue.poll()).val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

}


