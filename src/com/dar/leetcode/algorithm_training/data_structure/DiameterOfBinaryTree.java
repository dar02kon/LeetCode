package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 543. 二叉树的直径 https://leetcode.cn/problems/diameter-of-binary-tree/
 * @create :2023-03-04 15:58:00
 */
public class DiameterOfBinaryTree {

    public static void main(String[] args) {

    }

    int max = 0;
    // 统计节点数
    public int diameterOfBinaryTree(TreeNode root) {
        check(root);
        return max-2;
    }
    public int check(TreeNode root){
        if(root==null) return 0;
        int left = check(root.left)+1;
        int right = check(root.right)+1;
        max = Math.max(max,left+right+1);
        return Math.max(left,right);
    }

    // 统计路径数
    public int diameterOfBinaryTree2(TreeNode root) {
        check2(root);
        return max;
    }
    public int check2(TreeNode root){
        if(root==null) return 0;
        int left = check2(root.left);
        int right = check2(root.right);
        max = Math.max(max,left+right);
        return Math.max(left,right)+1;
    }
}
