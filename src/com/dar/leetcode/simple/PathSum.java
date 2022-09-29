package com.dar.leetcode.simple;

import java.util.*;

/**
 * @author :wx
 * @description : 路径总和 https://leetcode.cn/problems/path-sum/
 * @create :2022-09-29 20:05:00
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode t9 = new TreeNode(1,null,null);
        TreeNode t8 = new TreeNode(2,null,null);
        TreeNode t7 = new TreeNode(-1,null,null);
        TreeNode t6 = new TreeNode(4,null,t9);
        TreeNode t5 = new TreeNode(3,null,null);
        TreeNode t4 = new TreeNode(1,t7,null);

        TreeNode t3 = new TreeNode(-3,t5,t6);
        TreeNode t2 = new TreeNode(-2,t4,t5);
        TreeNode t1 = new TreeNode(1,t2,t3);
        System.out.println(new PathSum().hasPathSum(t1,-4));
    }


    /**
     * 需要使用两个队列，一个存储访问的节点，另一个存储当前路径之和
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * 递归求解
     * 节点为空则直接返回false
     * 若左节点和右节点都为空，则判断目前的路径之和是否与目标值相同
     * 相同，则直接返回false
     * 不相同，则继续递归前进（目标值需要减去节点值）
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }




}
