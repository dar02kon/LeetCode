package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 104. 二叉树的最大深度 https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 * @create :2023-02-25 09:23:00
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

    }
    // 递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left) + 1; // 求左子树的深度
        int right = maxDepth(root.right) + 1; // 求右子树的深度
        return Math.max(left, right); // 返回最大深度
    }

    // 层次遍历
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(); // 这一层的节点数，防止后面添加的节点产生干扰
            while (size-- > 0) { // 遍历这一层的节点
                TreeNode poll = queue.poll();
                // 添加下一层的节点
                if(poll.left!=null) queue.offer(poll.left);
                if(poll.right!=null) queue.offer(poll.right);
            }
            count++; // 计数
        }
        return count;
    }
}

class TreeNode {
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