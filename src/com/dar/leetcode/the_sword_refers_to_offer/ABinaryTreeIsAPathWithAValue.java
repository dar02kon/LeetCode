package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author :wx
 * @description : 剑指 Offer 34. 二叉树中和为某一值的路径 https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/?favorite=xb9nqhhg
 * @create :2022-12-03 12:58:00
 */
public class ABinaryTreeIsAPathWithAValue {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        route(root, target);
        return list;
    }

    int sum;
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    public void route(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        sum += root.val;
        record.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == target) {
                list.add(new ArrayList<>(record));
            }
            return;
        }
        if (root.left != null) {
            route(root.left, target);
            sum -= root.left.val;
            record.remove(record.size() - 1);
        }
        if (root.right != null) {
            route(root.right, target);
            sum -= root.right.val;
            record.remove(record.size() - 1);
        }
    }

    class Node {
        TreeNode node;
        List<Integer> list;
        int tot;

        Node(TreeNode _node, List<Integer> _list, int _tot) {
            node = _node;
            list = new ArrayList<>(_list);
            tot = _tot;
            list.add(node.val);
            tot += node.val;
        }
    }

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Node> d = new ArrayDeque<>();
        if (root != null)
            d.addLast(new Node(root, new ArrayList<>(), 0));
        while (!d.isEmpty()) {
            Node t = d.pollFirst();
            if (t.tot == target && t.node.left == null && t.node.right == null)
                ans.add(t.list);
            if (t.node.left != null)
                d.addLast(new Node(t.node.left, t.list, t.tot));
            if (t.node.right != null)
                d.addLast(new Node(t.node.right, t.list, t.tot));
        }
        return ans;
    }

}
