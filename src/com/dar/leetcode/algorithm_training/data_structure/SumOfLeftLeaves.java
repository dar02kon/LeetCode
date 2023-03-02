package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 404. 左叶子之和 https://leetcode.cn/problems/sum-of-left-leaves/description/
 * @create :2023-03-02 09:29:00
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {

    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        check(root.left, true);
        check(root.right, false);
        return result;
    }

    int result = 0;

    public void check(TreeNode root, boolean flag) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (flag) {
                result += root.val;
            }
        }
        if (root.left != null) {
            check(root.left, true);
        }
        if (root.right != null) {
            check(root.right, false);
        }
    }

}
