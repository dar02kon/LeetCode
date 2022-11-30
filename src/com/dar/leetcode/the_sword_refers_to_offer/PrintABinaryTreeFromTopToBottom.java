package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 32 - I. 从上到下打印二叉树 https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-30 08:57:00
 */
public class PrintABinaryTreeFromTopToBottom {

    public static void main(String[] args) {

    }

    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] result = new int[list.size()];
        int index = 0;
        for (Integer num : list) {
            result[index++] = num;
        }
        return result;
    }
}
