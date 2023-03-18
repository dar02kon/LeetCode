package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 538. 把二叉搜索树转换为累加树 https://leetcode.cn/problems/convert-bst-to-greater-tree/description/
 * @create :2023-03-18 13:47:00
 */
public class ConvertBstToGreaterTree {

    public static void main(String[] args) {

    }

    public TreeNode convertBST(TreeNode root) {
        add(root);
        return root;
    }

    int sum = 0;

    public void add(TreeNode root) {
        if (root == null) return;
        if (root.right != null) {
            add(root.right);
        }
        sum += root.val;
        root.val = sum;
        if (root.left != null) {
            add(root.left);
        }
    }


}

