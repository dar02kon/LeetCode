package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 669. 修剪二叉搜索树 https://leetcode.cn/problems/trim-a-binary-search-tree/description/
 * @create :2023-03-02 10:08:00
 */
public class TrimABinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val > high) return trimBST(root.left, low, high);
        if (root.val < low) return trimBST(root.right, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
