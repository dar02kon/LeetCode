package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author :wx
 * @description : 剑指 Offer 07. 重建二叉树 https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-10 11:59:00
 */
public class RebuildTheBinaryTree {

    public static void main(String[] args) {

    }

    Map<Integer, Integer> map = new HashMap<>();

    /**
     * 递归构建
     * @param preorder 前序
     * @param inorder 中序
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode head = new TreeNode();
        addNode2(head, preorder, inorder, 0, 0, preorder.length - 1);
        return head;
    }


    /**
     * 递归函数
     * @param head 根节点
     * @param preorder 前序序列
     * @param inorder 中序序列
     * @param index 前序序列下标
     * @param start 对应子树开始位置的下标（中序）
     * @param end 对应子树结束位置的下标（中序）
     */
    public void addNode(TreeNode head, int[] preorder, int[] inorder, int index, int start, int end) {
        for (int i = index; i < preorder.length; i++) {
            for (int j = start; j <= end; j++) {//寻找对应的根节点
                if (inorder[j] == preorder[i]) {
                    head.val = (preorder[i]);//添加节点
                    if (i + 1 == preorder.length) {//前序序列已经抵达边界
                        return;
                    }
                    if (start <= j - 1) {
                        head.left = new TreeNode();//添加左节点
                        addNode(head.left, preorder, inorder, i + 1, start, j - 1);//递归构建左子树
                    }
                    if (j + 1 <= end) {
                        head.right = new TreeNode();//添加右节点
                        addNode(head.right, preorder, inorder, i + 1, j + 1, end);//递归构建右子树
                    }
                    return;
                }
            }
        }
    }


    public void addNode2(TreeNode head, int[] preorder, int[] inorder, int index, int start, int end) {
        if (index == preorder.length) {
            return;
        }
        Integer j = map.get(preorder[index]);//定位根节点位置
        head.val = (preorder[index]);//添加根节点的值
        if (start <= j - 1) {
            head.left = new TreeNode();
            addNode2(head.left, preorder, inorder, index + 1, start, j - 1);//递归创建左子树
        }
        if (j + 1 <= end) {
            head.right = new TreeNode();
            addNode2(head.right, preorder, inorder, index + j - start + 1, j + 1, end);//递归创建右子树
        }
    }

    /**
     * 迭代构建
     * @param preorder 前序
     * @param inorder 后序
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
