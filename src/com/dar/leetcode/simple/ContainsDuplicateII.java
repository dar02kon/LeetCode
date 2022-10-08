package com.dar.leetcode.simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author :wx
 * @description : 存在重复元素 II https://leetcode.cn/problems/contains-duplicate-ii/
 * @create :2022-10-08 19:25:00
 */
public class ContainsDuplicateII {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        System.out.println(new ContainsDuplicateII().containsNearbyDuplicate2(nums,2));
    }

    /**
     * 使用HashMap来存储数字与位置
     * 如果当前数组已经出现并且满足距离要求，则返回true
     * 不满足，则更新HashMap
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                if(i-map.get(nums[i])<=k){
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }

    /**
     * 滑动窗口
     * 维护一个hash表来存储数字，hash表最多存储k个数字
     * 遍历数组，如果当前数字在hash表中存在，返回true
     * 否则，添加数字到hash表，如果表长超过k，则移除最前面的数字
     *
     * <p>
     *     为什么当前数字出现在hash表中就能判断它符合要求？
     *         因为hash表最大长度为k，最极端的情况就是hash的第一个数字与当前数字相等，并且hash表的长度为k，则索引之差为k,刚好满足条件
     *     如何保证hash表最大长度为k？
     *         当它的长度为k+1时，删除最前面的数字，此时最前面的数字为nums[i-k]
     * </p>
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if(set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;

    }


}
