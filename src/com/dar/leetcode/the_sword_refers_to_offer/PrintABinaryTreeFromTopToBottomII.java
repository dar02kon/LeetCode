package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 剑指 Offer 32 - II. 从上到下打印二叉树 II https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-30 09:33:00
 */
public class PrintABinaryTreeFromTopToBottomII {

    public static void main(String[] args) {

    }

    /**
     * 交换队列
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        Queue<TreeNode> firstQueue = new LinkedList<>();
        Queue<TreeNode> secondQueue = new LinkedList<>();
        if (root != null)
            firstQueue.add(root);
        while (!firstQueue.isEmpty()) {
            TreeNode node = firstQueue.poll();
            nums.add(node.val);
            if (node.left != null) {
                secondQueue.add(node.left);
            }
            if (node.right != null) {
                secondQueue.add(node.right);
            }
            if (firstQueue.isEmpty()) {
                list.add(new ArrayList<>(nums));
                nums.clear();
                Queue<TreeNode> t = firstQueue;
                firstQueue = secondQueue;
                secondQueue = t;
            }
        }
        return list;
    }

    /**
     * 计数，分层迭代
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                nums.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            list.add(nums);
        }
        return list;
    }
}
