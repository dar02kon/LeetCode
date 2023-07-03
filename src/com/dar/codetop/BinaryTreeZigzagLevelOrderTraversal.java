package com.dar.codetop;

import java.util.*;

/**
 * @author :wx
 * @description : 103. 二叉树的锯齿形层序遍历 https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/
 * @create :2023-07-03 21:56:00
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 层次遍历，每一层的节点用双向链表来存储以达到锯齿形
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> deque = new LinkedList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (flag) {
                    deque.addLast(node.val);
                } else {
                    deque.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            flag = !flag;
            list.add(deque);
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
