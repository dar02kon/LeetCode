package com.dar.leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 对称二叉树 https://leetcode.cn/problems/symmetric-tree/
 * @create :2022-09-26 20:19:00
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,t3,null);
        TreeNode t1 = new TreeNode(1,t2,t2);
        System.out.println(new SymmetricTree().isSymmetric(t1));
    }
    public boolean isSymmetric(TreeNode root) {
        if(root.left!=null&&root.right!=null){
            return compare(root.left,root.right);
        } else return root.left == null && root.right == null;
    }

    /**
     * 递归
     * @param left
     * @param right
     * @return
     */
    public boolean compare(TreeNode left,TreeNode right){
        if(left==null&&right==null){//对应为空返回true
            return true;
        } else if(left==null || right == null){//对应位置只有一个为空（不对称）
            return false;
        } else if(left.val!=right.val){//对应位置值不相等
            return false;
        } else {
            // 递归的比较 左节点的左孩子 和 右节点的右孩子
            // 以及比较  左节点的右孩子 和 右节点的左孩子
            return compare(left.left,right.right)&&compare(left.right,right.left);
        }
    }

    public boolean isSymmetric2(TreeNode root) {
        return check(root, root);
    }

    /**
     * 迭代
     * @param u
     * @param v
     * @return
     */
    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

}
