package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 55 - II. 平衡二叉树 https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-22 20:02:00
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        return depth(root)> -1;
    }

    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if(left-right>1||right-left>1||left==-1||right==-1){
            return -1;
        }
        return Math.max(left,right)+1;
    }
}
