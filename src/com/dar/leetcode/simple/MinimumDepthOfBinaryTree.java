package com.dar.leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 二叉树的最小深度 https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 * @create :2022-09-29 19:21:00
 */
public class MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,t3,null);
        TreeNode t1 = new TreeNode(1,t2,null);
        System.out.println(new MinimumDepthOfBinaryTree().minDepth2(t1));
    }

    /**
     * 递归 但这种自底向上的求法可能会遍历许多无关紧要的节点
     * 对于任意一个节点为根节点的二叉树的深度 = Math.max(左子树深度，右子树深度)+1
     * 二叉树的最小深度 = Math.min(left,right)+1，
     * 若左子树或右子树有一方深度为0
     * 则二叉树的最小深度 = Math.max(左子树深度，右子树深度)+1
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left==0||right==0?Math.max(left,right)+1:Math.min(left,right)+1;
    }

    /**
     * 自顶向下遍历（层次遍历） 能有效避免访问无关紧要的节点
     * 如果遇见某一节点的左子树与右子树均为空，则直接返回此时的深度
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return 0;
        }
        queue.offer(root);
        int minDepth = 1;
        while (!queue.isEmpty()){//队列不为空
            int size = queue.size();
            while (size>0){
                TreeNode node = queue.poll();
                if(node.left==null&&node.right==null){
                    return minDepth;
                }
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                size--;
            }
            minDepth++;
        }
        return minDepth;
    }
}
