package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 110. 平衡二叉树 https://leetcode.cn/problems/balanced-binary-tree/description/
 * @create :2023-03-01 09:09:00
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        check(root);
        return result;
    }
    boolean result = true;
    public int check(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = check(root.left)+1;
        int right = check(root.right)+1;
        if(right-left>1||left-right>1){
            result = false;
        }
        return Math.max(right,left);
    }
}
