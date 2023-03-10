package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 637. 二叉树的层平均值 https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 * @create :2023-03-10 09:44:00
 */
public class AverageOfLevelsInBinaryTree {

    public static void main(String[] args) {

    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if(root==null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int count = size;
            double sum = 0;
            while (size-- >0){
                TreeNode node = queue.poll();
                sum+=node.val;
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            list.add(sum/count);
        }
        return list;
    }
}
