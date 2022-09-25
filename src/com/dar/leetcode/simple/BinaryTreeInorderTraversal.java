package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :wx
 * <P>
 *     中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。若二叉树为空则结束返回，否则：
 *    （1）中序遍历左子树
 *    （2）访问根结点
 *    （3）中序遍历右子树
 * </P>
 * @description : 二叉树的中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * @create :2022-09-25 20:41:00
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(5,null,null);
        TreeNode t4 = new TreeNode(4,null,null);
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,t3,null);
        TreeNode t1 = new TreeNode(1,null,t2);
        List<Integer> list = new BinaryTreeInorderTraversal().inorderTraversal(t1);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 非递归写法
     * 需要设置一个flag，为true表示head的左子树未访问
     * 使用栈来存储节点，如果左节点不为空且未访问，则将节点入栈，指针左移
     * 若左节点为空，将节点的值加入集合，
     * 若右节点不为空，则指针右移，flag为true，若右节点为空，指针指向栈顶对应的节点（栈空则直接退出）
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        boolean flag = true;
        while (root!=null){
            if(root.left!=null&&flag){
                stack.add(root);
                root = root.left;
            } else {
                list.add(root.val);
                if(root.right!=null){
                    root = root.right;
                    flag = true;
                } else {
                    if(!stack.empty()){
                        root = stack.pop();
                        flag = false;
                    } else {
                        break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 递归写法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      addVal(root,list);
      return list;
    }

    public void addVal(TreeNode root, List<Integer> list) {
        if (root == null) {//为空直接返回
            return;
        }
        addVal(root.left,list);//访问左子树
        list.add(root.val);//添加根节点的值到集合
        addVal(root.right, list);//访问右子树
    }

}


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}
