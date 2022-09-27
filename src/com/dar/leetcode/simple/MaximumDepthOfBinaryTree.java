package com.dar.leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author :wx
 * @description : 二叉树的最大深度 https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * @create :2022-09-27 19:26:00
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode t7 = new TreeNode(7,null,null);
        TreeNode t6 = new TreeNode(6,null,null);
        TreeNode t5 = new TreeNode(5,t6,t7);

        TreeNode t12= new TreeNode(12,null,null);
        TreeNode t11= new TreeNode(11,null,null);
        TreeNode t10 = new TreeNode(10,null,t11);
        TreeNode t9 = new TreeNode(9,null,null);
        TreeNode t8 = new TreeNode(8,null,t10);


        TreeNode t4 = new TreeNode(4,null,t9);
        TreeNode t3 = new TreeNode(3,t4,t8);
        TreeNode t2 = new TreeNode(2,t5,null);
        TreeNode t1 = new TreeNode(1,t2,t3);
        System.out.println(new MaximumDepthOfBinaryTree().maxDepth2(t1));
    }


    /**
     * 树的深度 = max(左子树深度,右子树深度) + 1;
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);//左子树
            int right = maxDepth(root.right);//右子树
            return Math.max(left, right) + 1;
        }
    }

    /**
     * 层次遍历
     * 使用队列来记录每一层的节点
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){//该层的每一个都尝试往下探索
                TreeNode pop = queue.poll();
                if(pop.left!=null){
                    queue.offer(pop.left);
                }
                if(pop.right!=null){
                    queue.offer(pop.right);
                }
                size--;
            }
            count++;
        }
        return count;
    }

}
