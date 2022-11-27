package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 28. 对称的二叉树 https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-27 17:06:00
 */
public class SymmetricBinaryTree {

    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root){
        return check(root,root);
    }

    public boolean check(TreeNode p, TreeNode q){
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }

        return p.val==q.val&&check(p.left,q.right)&&check(p.right,q.left);
    }

    public boolean isSymmetric2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p = root;
        TreeNode q = root;
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()){
            p = queue.poll();
            q = queue.poll();
            if(p==null&&q==null){
                continue;
            }
            if(p==null||q==null||p.val!=q.val){
                return false;
            }
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }
}
