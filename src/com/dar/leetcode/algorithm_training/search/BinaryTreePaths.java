package com.dar.leetcode.algorithm_training.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 257. 二叉树的所有路径 https://leetcode.cn/problems/binary-tree-paths/description/
 * @create :2023-01-15 09:48:00
 */
public class BinaryTreePaths {

    static class TreeNode {
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

    public static void main(String[] args) {

    }

    /**
     * DFS
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);
        return list;
    }

    public void dfs(TreeNode root, String s, List<String> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(s + root.val);
            return;
        }
        s += root.val + "->";
        dfs(root.left, s, list);
        dfs(root.right, s, list);
    }

    /**
     * BFS
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> nodeQueue = new LinkedList<>();//存储遍历的节点
        Queue<String> pathQueue = new LinkedList<>();//存储路径

        nodeQueue.add(root);
        pathQueue.add(root.val + "");

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {//叶子节点
                list.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    pathQueue.add(path + "->" + node.left.val);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    pathQueue.add(path + "->" + node.right.val);
                }
            }
        }
        return list;
    }
}
