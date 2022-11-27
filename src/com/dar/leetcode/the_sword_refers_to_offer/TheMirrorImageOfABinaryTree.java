package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 27. 二叉树的镜像 https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-27 15:49:00
 */
public class TheMirrorImageOfABinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 层次遍历
     * 遍历每一个节点，将其左子树与右子树进行互换
     * 从上往下的一个过程
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();//使用队列来存储节点
        TreeNode p = root;
        while (p != null) {
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
            TreeNode target = p.right;
            p.right = p.left;
            p.left = target;
            if (queue.isEmpty()) {
                break;
            }
            p = queue.poll();
        }
        return root;
    }

    /**
     * 递归交换
     * 相当于从下往上的一个过程
     * @param root
     * @return
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree2(root.left);
        TreeNode right = mirrorTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
