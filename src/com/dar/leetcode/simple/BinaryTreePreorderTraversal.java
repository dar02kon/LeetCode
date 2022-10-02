package com.dar.leetcode.simple;

import java.util.*;

/**
 * @author :wx
 * @description : 二叉树的前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * @create :2022-10-02 20:33:00
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3, null, null);
        TreeNode t2 = new TreeNode(2, t3, null);
        TreeNode t1 = new TreeNode(1, null, t2);

        System.out.println(new BinaryTreePreorderTraversal().preorderTraversal2(t1));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addNode(list, root);
        return list;
    }

    /**
     * 递归
     * 访问根节点
     * 访问左子树
     * 访问右子树
     *
     * @param list
     * @param root
     */
    public void addNode(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        addNode(list, root.left);
        addNode(list, root.right);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            while (root != null) {
                list.add(root.val);
                stack.add(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return list;
    }
}
