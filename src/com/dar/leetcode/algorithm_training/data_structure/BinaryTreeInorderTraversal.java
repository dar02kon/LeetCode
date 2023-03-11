package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :wx
 * @description : 94. 二叉树的中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 * @create :2023-03-11 10:23:00
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            p = pop.right;
        }
        return list;
    }
}
