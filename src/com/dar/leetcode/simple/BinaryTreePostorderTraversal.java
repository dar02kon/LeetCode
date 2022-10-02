package com.dar.leetcode.simple;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :wx
 * @description : 二叉树的后序遍历  https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * @create :2022-10-02 21:30:00
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3, null, null);
        TreeNode t2 = new TreeNode(2, t3, null);
        TreeNode t1 = new TreeNode(1, null, t2);

        System.out.println(new BinaryTreePostorderTraversal().postorderTraversal2(t1));
    }

    /**
     * 递归
     * 访问左子数
     * 访问右子树
     * 访问根节点
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addNode(list, root);
        return list;
    }

    public void addNode(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        addNode(list, root.left);
        addNode(list, root.right);
        list.add(root.val);
    }

    /**
     * 后序遍历中，从栈中弹出的节点，我们只能确定其左子树肯定访问完了，
     * 但是无法确定右子树是否访问过。
     * 因此，我们在后序遍历中，引入了一个prev来记录历史访问记录
     * 当访问完一棵子树的时候，我们用prev指向该节点。
     * 这样，在回溯到父节点的时候，
     * 我们可以依据prev是指向左子节点，还是右子节点，来判断父节点的访问情况。
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.empty() || root != null) {
            while (root != null ) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root == prev){
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.add(root);
                root = root.right;
            }
        }
        return list;
    }
}
