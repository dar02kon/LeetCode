package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 226. 翻转二叉树 https://leetcode.cn/problems/invert-binary-tree/description/
 * @create :2023-02-25 09:43:00
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    // 递归
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 非递归
    public TreeNode invertTree2(TreeNode root) {
        if(root==null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(poll.left!=null){
                queue.offer(poll.left);
            }
            if(poll.right!=null){
                queue.offer(poll.right);
            }
            TreeNode temp = poll.left;
            poll.left = poll.right;
            poll.right = temp;
        }
        return root;
    }

}
