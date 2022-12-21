package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 55 - I. 二叉树的深度 https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/?favorite=xb9nqhhg
 * @create :2022-12-21 20:52:00
 */
public class TheDepthOfTheBinaryTree {

    public static void main(String[] args) {

    }

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }

    public int maxDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return 0;
        }
        int count = 0;
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){
                TreeNode poll = queue.poll();
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
                size--;
            }
            count++;
        }
        return count;
    }
}
