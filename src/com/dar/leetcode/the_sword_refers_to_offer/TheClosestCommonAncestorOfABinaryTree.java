package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author :wx
 * @description : 剑指 Offer 68 - II. 二叉树的最近公共祖先 https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/?favorite=xb9nqhhg
 * @create :2023-01-03 11:50:00
 */
public class TheClosestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {

    }
    Map<Integer,TreeNode> map = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public void dfs(TreeNode root){
        if (root.left!=null){
            map.put(root.left.val,root);
            dfs(root.left);
        }
        if (root.right!=null){
            map.put(root.right.val,root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p!=null){
            visited.add(p.val);
            p = map.get(p.val);
        }
        while (q!=null){
            if(visited.contains(q.val)){
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right,p,q);
        if(left==null) return right;
        if(right==null) return left;
        return root;
    }

}
