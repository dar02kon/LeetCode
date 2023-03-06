package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 337. 打家劫舍 III https://leetcode.cn/problems/house-robber-iii/description/
 * @create :2023-03-06 09:35:00
 */
public class HouseRobberIII {

    public static void main(String[] args) {

    }

    public int rob(TreeNode root) {
        int[] check = check(root);
        return Math.max(check[0],check[1]);
    }

    public int[] check(TreeNode root){
        if(root==null) return new int[]{0,0};
        int[] left = check(root.left);
        int[] right = check(root.right);
        int selected = root.val + left[1]+right[1];
        int notSelected = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return new int[]{selected,notSelected};
    }

}
