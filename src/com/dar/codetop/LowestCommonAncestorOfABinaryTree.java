package com.dar.codetop;


/**
 * @author :wx
 * @description : 236. 二叉树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * @create :2023-07-04 21:41:00
 */
public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // root节点等于任意一个节点直接返回
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // 从左子树中寻找目标节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 从右子树中寻找目标节点
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左子树若没有目标节点，右子树有，则目标节点都在右子树中，直接返回第一个找到的目标节点即可
        // 右子树没有目标节点，左子树有，同上
        // 左右子树都有，返回根节点root
        return left == null ? right : (right == null ? left : root);
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
