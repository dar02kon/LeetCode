package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 剑指 Offer 36. 二叉搜索树与双向链表 https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/description/
 * @create :2022-12-05 15:10:00
 */
public class BinarySearchTreesAndBidirectionalLinkedLists {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {

    }

    /**
     * 中序遍历 + 集合
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        transfer(root);
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            node.left = i - 1 == -1 ? list.get(list.size() - 1) : list.get(i - 1);
            node.right = i + 1 == list.size() ? list.get(0) : list.get(i + 1);
        }
        return list.size() == 0 ? null : list.get(0);
    }

    List<Node> list = new ArrayList<>();

    public void transfer(Node root) {
        if (root == null) {
            return;
        }
        transfer(root.left);
        list.add(root);
        transfer(root.right);
    }


    Node head, pre;

    /**
     * 中序遍历 + 递归
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        transfer2(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void transfer2(Node root) {
        if (root == null) {
            return;
        }
        transfer2(root.left);
        if (pre == null) {
            head = root;
        } else {
            root.left = pre;
            pre.right = root;
        }
        pre = root;
        transfer2(root.right);
    }
}
