package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author :wx
 * @description : 145. 二叉树的后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/description/
 * @create :2023-03-11 10:09:00
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if(pop.left!=null) stack.push(pop.left);
            if(pop.right!=null) stack.push(pop.right);
        }
        Collections.reverse(list);
        return list;
    }
}
