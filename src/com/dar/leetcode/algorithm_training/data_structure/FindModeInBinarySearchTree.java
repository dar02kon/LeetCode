package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 501. 二叉搜索树中的众数 https://leetcode.cn/problems/find-mode-in-binary-search-tree/description/
 * @create :2023-03-18 14:17:00
 */
public class FindModeInBinarySearchTree {

    public static void main(String[] args) {

    }

    int max = Integer.MIN_VALUE;
    int count = 0;
    int pre = Integer.MIN_VALUE;
    List<Integer> list = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inOrder(root);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (pre == Integer.MIN_VALUE) {
            pre = root.val;
        }
        if (pre == root.val) {
            count++;
        } else {
            count = 1;
        }
        if (count > max) {
            list.clear();
            list.add(root.val);
            max = count;
        } else if (count == max) {
            list.add(root.val);
        }
        pre = root.val;
        inOrder(root.right);
    }

}
