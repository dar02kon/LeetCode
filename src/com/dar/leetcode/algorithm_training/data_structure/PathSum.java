package com.dar.leetcode.algorithm_training.data_structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 112. 路径总和 https://leetcode.cn/problems/path-sum/description/
 * @create :2023-02-28 08:53:00
 */
public class PathSum {

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        return check(root,targetSum);
    }
    public boolean check(TreeNode root, int targetSum) {
        if(root.left==null&&root.right==null){
            return targetSum-root.val==0;
        }
        if(root.left!=null&&root.right!=null){
            return check(root.left,targetSum-root.val) || check(root.right,targetSum-root.val);
        } else if(root.left!=null){
            return check(root.left,targetSum-root.val);
        } else {
            return check(root.right,targetSum-root.val);
        }
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null&&root.val==targetSum) return true;
        return hasPathSum2(root.left,targetSum-root.val) || hasPathSum2(root.right,targetSum-root.val);
    }

    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.offer(root);
        sumQueue.add(root.val);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int sum = sumQueue.poll();
            if(node.left==null&&node.right==null){
                if(sum==targetSum)
                    return true;
            }
            if(node.left!=null){
                nodeQueue.offer(node.left);
                sumQueue.offer(sum+node.left.val);
            }
            if(node.right!=null){
                nodeQueue.offer(node.right);
                sumQueue.offer(sum+node.right.val);
            }
        }
        return false;
    }

}
