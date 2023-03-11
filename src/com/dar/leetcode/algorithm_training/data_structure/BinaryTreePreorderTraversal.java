package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :wx
 * @description : 144. 二叉树的前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * @create :2023-03-11 09:47:00
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root==null) return list;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if(pop.right!=null) stack.push(pop.right);
            if(pop.left!=null) stack.push(pop.left);
        }
        return list;
    }

}
