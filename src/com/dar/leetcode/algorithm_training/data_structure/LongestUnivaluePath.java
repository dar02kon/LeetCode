package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 687. 最长同值路径 https://leetcode.cn/problems/longest-univalue-path/description/
 * @create :2023-03-06 09:04:00
 */
public class LongestUnivaluePath {

    public static void main(String[] args) {

    }

    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        check(root, root.val);
        return max;
    }

    public int check(TreeNode root, int val) {
        if (root == null) return 0;
        if (root.val != val) {
            check(root, root.val);
            return 0;
        }
        int left = check(root.left, val);
        int right = check(root.right, val);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
