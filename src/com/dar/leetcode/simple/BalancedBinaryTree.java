package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 平衡二叉树 https://leetcode.cn/problems/balanced-binary-tree/
 * @create :2022-09-28 21:08:00
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {

        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,t3,null);
        TreeNode t1 = new TreeNode(1,t2,null);

        System.out.println(new BalancedBinaryTree().isBalanced2(t1));
    }

    /**
     * 下面这种写法太丑陋了
     * 大概就是分别求左子树和右子树的深度进行比较
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        TreeNode t = new TreeNode(1);
        try {
            maxDepth(root,t);
        } catch (Exception ignored){

        }
        return t.val==1;
    }

    public int maxDepth(TreeNode root,TreeNode t){
        if(root==null){
            return 0;
        } else {
            int left = maxDepth(root.left,t);
            int right = maxDepth(root.right,t);
            if(left-right>1||left-right< -1){
                t.val = 0;
                throw new RuntimeException();
            }
            return Math.max(left,right)+1;
        }
    }

    /**
     * 仍然是递归求解左右子树的深度
     * 但如果出现不是平衡二叉树的条件则直接返回-1（我没有想到以优雅的姿态直接跳出函数）
     * 判断最后的结果是否大于-1
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
       return maxDepth2(root)> -1;
    }
    public int maxDepth2(TreeNode root){
        if(root==null){
            return 0;
        } else {
            int left = maxDepth2(root.left);
            int right = maxDepth2(root.right);
            if(left-right>1||left-right< -1||left==-1||right==-1){//不符合平衡二叉树的条件
                return -1;
            }else {
                return Math.max(left,right)+1;
            }
        }
    }


}
