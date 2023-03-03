package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 108. 将有序数组转换为二叉搜索树 https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/
 * @create :2023-03-03 13:12:00
 */
public class ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return create(nums, 0, nums.length - 1);
    }

    public TreeNode create(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = create(nums, left, mid - 1);
        root.right = create(nums, mid + 1, right);
        return root;
    }
}
