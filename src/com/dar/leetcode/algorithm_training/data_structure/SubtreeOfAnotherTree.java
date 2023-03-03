package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 572. 另一棵树的子树 https://leetcode.cn/problems/subtree-of-another-tree/description/
 * @create :2023-03-03 12:24:00
 */
public class SubtreeOfAnotherTree {

    public static void main(String[] args) {

    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (check(poll, subRoot)) return true;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }

        }
        return false;
    }

    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val == subRoot.val && check(root, subRoot)) return true;
        return isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);
    }

    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }



}
