package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 236. 二叉树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * @create :2023-03-05 10:20:00
 */
public class LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root.val==p.val||root.val==q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left==null?right:(right==null?left:root);
    }
}
