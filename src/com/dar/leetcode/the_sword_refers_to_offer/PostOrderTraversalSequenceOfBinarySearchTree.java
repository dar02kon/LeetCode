package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Stack;

/**
 * @author :wx
 * @description : 剑指 Offer 33. 二叉搜索树的后序遍历序列 https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/?favorite=xb9nqhhg
 * @create :2022-12-02 10:43:00
 */
public class PostOrderTraversalSequenceOfBinarySearchTree {

    public static void main(String[] args) {
        int[] num = {1, 3, 2, 4, 6, 5};
        boolean check = new PostOrderTraversalSequenceOfBinarySearchTree().verifyPostorder(num);
        System.out.println(check);
    }

    public boolean verifyPostorder(int[] postorder) {
        return check(postorder, 0, postorder.length - 1);
    }

    public boolean check(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int index = start;
        while (postorder[index] < postorder[end]) {
            index++;
        }
        int i = index;
        while (postorder[i] > postorder[end]) {
            i++;
        }
        return i == end && check(postorder, start, index - 1) && check(postorder, index, end - 1);
    }

    public boolean verifyPostorder2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}
