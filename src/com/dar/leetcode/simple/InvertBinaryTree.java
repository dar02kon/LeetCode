package com.dar.leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 翻转二叉树 https://leetcode.cn/problems/invert-binary-tree/
 * @create :2022-10-09 13:15:00
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 用队列来存储每一层的节点
     * 在添加某一节点的左右节点之后，将其互换
     * 翻转二叉树其实就是将每一个节点的左子树与右子树互换
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(poll.left!=null){
                queue.add(poll.left);
            }
            if(poll.right!=null){
                queue.add(poll.right);
            }
            TreeNode t = poll.left;
            poll.left = poll.right;
            poll.right = t;
        }
        return root;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        invert(root);
        return root;
    }
    public void invert(TreeNode root){
        if(root==null){
            return;
        }
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        invert(root.left);
        invert(root.right);
    }
}
