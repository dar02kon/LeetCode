package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 671. 二叉树中第二小的节点 https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree/description/
 * @create :2023-03-04 16:40:00
 */
public class SecondMinimumNodeInABinaryTree {

    public static void main(String[] args) {

    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        int left = root.left.val;
        int right = root.right.val;
        if (root.val == left) left = findSecondMinimumValue(root.left);
        if (root.val == right) right = findSecondMinimumValue(root.right);
        if (left != -1 && right != -1) return Math.min(left, right);
        return left != -1 ? left : right;
    }
}
