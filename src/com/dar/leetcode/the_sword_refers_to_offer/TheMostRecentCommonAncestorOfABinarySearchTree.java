package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先 https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-03 09:56:00
 */
public class TheMostRecentCommonAncestorOfABinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root!=null){
            if(root.val<=Math.max(p.val,q.val)&&root.val>=Math.min(p.val,q.val)){
                return root;
            } else if (root.val<=Math.min(p.val,q.val)){
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }
}
