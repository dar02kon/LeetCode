package com.dar.leetcode.medium;

import java.util.*;

/**
 * @author :wx
 * @description : 组合总和 https://leetcode.cn/problems/combination-sum/
 * @create :2022-10-31 13:03:00
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] nums = {2,4,3};
        List<List<Integer>> list = new CombinationSum().combinationSum(nums, 6);
        for (List<Integer> integerList : list) {
            System.out.println(integerList);
        }
    }
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    /**
     * 回溯搜索（树状结构）
     * 从右往左进行搜索，但寻找的组合还是从左往右的 不过倒过来了
     * 对于[2,4,3]，第一个符合要求的组合是[3,3]然后是[2,4] [2,2,2]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        getRecords(candidates,target,0);
        return list;
    }
    public void getRecords(int[] candidates,int target,int index){
        if(index==candidates.length){
            return;
        }
        if(0==target){
            list.add(new ArrayList<>(record));
            return;
        }
        getRecords(candidates,target,index+1);
        if(target-candidates[index]>=0){
            record.add(candidates[index]);
            getRecords(candidates,target-candidates[index],index);
            record.remove(record.size()-1);
        }
    }

    /**
     * 回溯搜素（树状结构）
     * 寻找目标值，继续递归寻找 目标值-当前节点值
     * 从前往后找出所有的组合 同一个 数字可以 无限制重复被选取
     * 对于[2,4,3]，[2,2,2]是第一组符合条件的，然后是[2,4]与[3,3]
     * 若第一层循环从第一个节点开始则其递归的起点仍然是第一个节点，
     * 确保同一个节点能再次参与，之前的节点不参与
     * 找到目标值或者确定不满足目标值的情况需要进行回溯，删除之前添加的节点回到初始状态
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //深度搜索
        dfs(candidates,0, target);
        //返回结果
        return list;
    }
    public void dfs(int[] candidates,int begin,int target){
        //如果此时目标元素经过几次深度递归，减值，
        //就说明，数组中不存在能相加等于目标数组的元素集合
        if(target < 0){
            return;
        }
        //如果刚好减到0，说明此时路径上的元素，相加等于目标元素。
        //此时路径上的元素就符合条件，将他们加入返回结果中，并退出此次递归
        if(target == 0){
            list.add(new ArrayList<>(record));
            return;
        }
        //遍历元素，这里的i必须要跟递归层数保持一致，要不然剪枝时，会造成重复元素
        for(int i = begin; i< candidates.length; i++){
            //将路径上的元素加入结果集合中
            record.add(candidates[i]);
            //在进行一轮剪枝到根节点的时候，下一轮的搜索的启点就不能包括上一次搜索的下标了
            //此时在拼接重复元素的时候，起点只能是大于等于当前元素的下标。
            dfs(candidates, i, target-candidates[i]);
            //将元素进行删除，也叫剪枝，
            //这里必须从队列的尾部开始删除，这样才能达到从底层逐层删除
            record.remove(record.size()-1);
        }
    }
}
