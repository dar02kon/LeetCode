package com.dar.codetop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 199. 二叉树的右视图 https://leetcode.cn/problems/binary-tree-right-side-view/
 * @create :2023-07-20 20:31:00
 */
public class BinaryTreeRightSideView {
    /**
     * 层次遍历，取每一层最右边的节点即可
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            list.add(queue.peek().val);
            // 遍历完一层
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return list;
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

