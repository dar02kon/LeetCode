package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 530. 二叉搜索树的最小绝对差 https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * @create :2023-03-12 11:07:00
 */
public class MinimumAbsoluteDifferenceInBST {

    public static void main(String[] args) {

    }

    int min = Integer.MAX_VALUE;
    int pre = -1;
    public int getMinimumDifference(TreeNode root) {
      inOrder(root);
      return min;
    }

    private void inOrder(TreeNode root) {
        if(root==null) return;
        inOrder(root.left);
        if(pre!=-1) min = Math.min(min, Math.abs(root.val - pre));
        pre = root.val;
        inOrder(root.right);
    }


}
