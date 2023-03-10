package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 513. 找树左下角的值 https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @create :2023-03-10 10:08:00
 */
public class FindBottomLeftTreeValue {

    public static void main(String[] args) {

    }

    // 层次遍历 从左往右
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size - 1;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (size == count) {
                    result = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    // 层次遍历 从右往左
    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }
}
