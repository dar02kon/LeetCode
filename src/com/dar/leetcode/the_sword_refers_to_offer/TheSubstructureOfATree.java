package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 26. 树的子结构 https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/?favorite=xb9nqhhg
 * @create :2022-11-26 19:28:00
 */
public class TheSubstructureOfATree {
    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2, null, null);
        TreeNode t1 = new TreeNode(1, null, null);
        TreeNode t5 = new TreeNode(5, null, null);
        TreeNode t4 = new TreeNode(4, t1, t2);
        TreeNode t3 = new TreeNode(3, t4, t5);

        boolean subStructure = new TheSubstructureOfATree().isSubStructure(t3, t4);
        System.out.println(subStructure);
    }


    public boolean isSubStructure(TreeNode A, TreeNode B) {//先序遍历A的每一个节点
        return (A != null && B != null) && (traversal(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    /**
     * 根据根节点依次比较每个节点
     * @param A
     * @param B
     * @return
     */
    public boolean traversal(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return traversal(A.left, B.left) && traversal(A.right, B.right);
    }


}
