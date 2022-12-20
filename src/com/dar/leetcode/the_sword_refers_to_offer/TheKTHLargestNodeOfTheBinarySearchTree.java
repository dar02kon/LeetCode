package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 54. 二叉搜索树的第k大节点 https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/?favorite=xb9nqhhg
 * @create :2022-12-20 22:30:00
 */
public class TheKTHLargestNodeOfTheBinarySearchTree {

    public static void main(String[] args) {

    }
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null)
            return;
        dfs(root.right);
        if(k == 0)
            return;
        if(--k == 0)
            res = root.val;
        dfs(root.left);
    }

}
