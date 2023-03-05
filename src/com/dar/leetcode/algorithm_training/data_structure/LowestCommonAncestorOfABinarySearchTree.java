package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 235. 二叉搜索树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * @create :2023-03-05 10:00:00
 */
public class LowestCommonAncestorOfABinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((p.val<=root.val&&q.val>=root.val)||(p.val>=root.val&&q.val<=root.val)){
            return root;
        }
        if(p.val<root.val) return lowestCommonAncestor(root.left,p,q);
        return lowestCommonAncestor(root.right,p,q);
    }
}
