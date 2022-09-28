package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * <p>
 *     平衡二叉搜索树的任何结点的左子树和右子树高度最多相差1。
 *     二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
 *     若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 *     若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 *     它的左、右子树也分别为二叉排序树。
 * </p>
 * @description : 将有序数组转换为二叉搜索树 https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 * @create :2022-09-28 19:32:00
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4};
        TreeNode treeNode = new ConvertSortedArrayToBinarySearchTree().sortedArrayToBST(nums);
        System.out.println(treeNode.val);
    }

    /**
     * 总是选取中间位置为根节点，递归添加
     * 递归真是头大
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
       return addNode(nums,0, nums.length-1);
    }
    public TreeNode addNode(int[] nums,int left,int right){
        if(left>right){//返回空
            return null;
        }
        int mid = (left+right)/2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = addNode(nums,left,mid-1);
        head.right = addNode(nums,mid+1,right);
        return head;
    }
}
