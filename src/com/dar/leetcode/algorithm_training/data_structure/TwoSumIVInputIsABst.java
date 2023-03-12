package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 653. 两数之和 IV - 输入二叉搜索树 https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/description/
 * @create :2023-03-12 10:52:00
 */
public class TwoSumIVInputIsABst {

    public static void main(String[] args) {

    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root,list);
        int left = 0,right = list.size()-1;
        while (left<right){
            int sum = list.get(left)+list.get(right);
            if(sum==k){
                return true;
            } else if(sum>k){
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public void inOrder(TreeNode root,List<Integer> list){
        if(root==null) return;
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);
    }

}
