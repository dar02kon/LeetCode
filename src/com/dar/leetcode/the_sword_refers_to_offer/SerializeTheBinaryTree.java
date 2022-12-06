package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 37. 序列化二叉树 https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/?favorite=xb9nqhhg
 * @create :2022-12-06 18:18:00
 */
public class SerializeTheBinaryTree {

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(5, null, null);
        TreeNode t4 = new TreeNode(4, t5, null);
        TreeNode t3 = new TreeNode(3, t4, null);
        TreeNode t2 = new TreeNode(2, t3, null);
        TreeNode t1 = new TreeNode(1, t2, null);
        String serialize = new SerializeTheBinaryTree().serialize(t1);
        System.out.println(serialize);
        TreeNode node = new SerializeTheBinaryTree().deserialize(serialize);
        System.out.println(node.val);

    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node!=null){
                result.append(node.val).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                result.append("! ");
            }
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] s = data.split(" ");
        TreeNode head = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
         int i=1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!s[i].equals("!")) {
                node.left = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.left);
            }
            i++;
            if(!s[i].equals("!")) {
                node.right = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.right);
            }
            i++;
        }
        return head;
    }
}
