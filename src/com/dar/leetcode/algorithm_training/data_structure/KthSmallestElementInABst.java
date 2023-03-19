package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Stack;

/**
 * @author :wx
 * @description : 230. 二叉搜索树中第K小的元素 https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/
 * @create :2023-03-19 13:14:00
 */
public class KthSmallestElementInABst {

    public static void main(String[] args) {

    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if(k==1) return root.val;
            k--;
            root = root.right;
        }
        return -1;
    }

    public int kthSmallest2(TreeNode root, int k) {
        int count = count(root.left);
        if(count==k-1) return root.val;
        if(count>k-1) return kthSmallest2(root.left,k);
        return kthSmallest2(root.right,k-count-1);
    }

    public int count(TreeNode root){
        if(root==null) return 0;
        return count(root.left)+count(root.right)+1;
    }


}
