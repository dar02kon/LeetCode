package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :wx
 * @description : 全排列 https://leetcode.cn/problems/permutations/
 * @create :2022-11-04 14:47:00
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> permute = new Permutations().permute2(nums);
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    /**
     * 递归回溯
     * <p>
     *     对于数组 [1，2，3]的全排列如下
     *     1 + [2,3]的全排列
     *     2 + [1,3]的全排列
     *     3 + [1,2]的全排列
     *     对于[2,3]的全排列同上
     *     存在递归
     *     若循环每次都从0开始重复遍历，则需要考虑去重（即一个数字只用一次）
     * </p>
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        getRecord(nums);
        return list;
    }
    public void getRecord(int[] nums){
        if(record.size()== nums.length){//满足条件
            list.add(new ArrayList<>(record));
            return;
        }
        for (int num : nums) {//从0开始遍历
            if (record.contains(num)) {//去重
                continue;
            }
            record.add(num);//添加数值
            getRecord(nums);
            record.remove(record.size() - 1);//回溯删除之前添加的数据
        }

    }

    /**
     * 改善去重，优化代码
     * <P>
     *     对于[1,2,3]，我们可以设置一个边界first来分割数组，[0,first-1]是已填过的数的集合，[first,n−1]是待填的数的集合。
     *     尝试用 [first,n−1]里的数去填第 first个数，假设待填的数（>=first）的下标为 i，那么填完以后我们将第 i个数和第 first个数交换，
     *     即能使得在填第 first+1个数的时候 nums 数组的 [0,first]部分为已填过的数，[first+1,n−1]为待填的数，
     *     回溯的时候交换回来即能完成撤销操作。
     * 链接：https://leetcode.cn/problems/permutations/solutions/218275/quan-pai-lie-by-leetcode-solution-2/
     * </P>
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        for (int num : nums) {
            record.add(num);
        }
        int n = nums.length;
        backtrack(n,0);
        return list;
    }
    public void backtrack(int n, int first) {
        // 所有数都填完了
        if (first == n) {
            list.add(new ArrayList<Integer>(record));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(record, first, i);
            // 继续递归填下一个数
            backtrack(n,  first + 1);
            // 撤销操作
            Collections.swap(record, first, i);
        }
    }

}
