package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.*;

/**
 * @author :wx
 * @description : 剑指 Offer 32 - III. 从上到下打印二叉树 III https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/?favorite=xb9nqhhg
 * @create :2022-12-01 10:19:00
 */
public class PrintABinaryTreeFromTopToBottomIII {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> linkedList = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean flag = true;
        if (root != null)
            linkedList.add(root);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (flag) {
                    node = linkedList.remove(0);
                    if (node.left != null) {
                        linkedList.add(node.left);
                    }
                    if (node.right != null) {
                        linkedList.add(node.right);
                    }
                } else {
                    node = linkedList.remove(size - i - 1);
                    if (node.right != null) {
                        linkedList.add(size - i - 1, node.right);
                    }
                    if (node.left != null) {
                        linkedList.add(size - i - 1, node.left);
                    }
                }
                nums.add(node.val);
            }
            list.add(nums);
            flag = !flag;
        }
        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    deque.offerLast(node.val);
                } else {
                    deque.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(new LinkedList<>(deque));
            flag = !flag;
        }
        return list;
    }
}
