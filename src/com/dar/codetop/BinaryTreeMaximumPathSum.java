package com.dar.codetop;

/**
 * @author :wx
 * @description : 124. 二叉树中的最大路径和 https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 * @create :2023-07-29 18:38:00
 */
public class BinaryTreeMaximumPathSum {
    /**
     * 递归 寻找左子树与右子树中的最大路径和
     * 对于一个节点返回给上一层时，只有三种情况：
     * 左子树的最大路径+根节点，
     * 左子树的最大路径+根节点，
     * 根节点，
     * 最坏情况就是不返回（负数，抛弃这个节点的分支）
     */
    public int maxPathSum(TreeNode root) {
        search(root);
        return max;
    }

    private int max = Integer.MIN_VALUE;

    private int search(TreeNode node) {
        if (node == null) return 0;
        // 左子树路径
        int left = search(node.left);
        // 右子树路径
        int right = search(node.right);
        // 每返回到一个节点，需要更新最大路径和
        max = Math.max(max, left + right + node.val);
        // 返回到上一层需要返回和大的路径，实在不行就返回0，抛弃这个分支
        if (node.val < 0) return Math.max(Math.max(left, right) + node.val, 0);
        return Math.max(Math.max(left, right) + node.val, node.val);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
