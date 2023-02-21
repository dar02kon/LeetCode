package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 287. 寻找重复数 https://leetcode.cn/problems/find-the-duplicate-number/description/
 * @create :2023-02-21 09:18:00
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {

    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0],fast = nums[nums[0]];
        while (slow!=fast){ // 快慢指针第一次相遇
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0; // 移动到起点
        while (slow!=fast){ // 速度相同再次相遇即为环的起点
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate2(int[] nums) {
        int left = 1,right = nums.length-1; // left与right是需要寻找的数字，不是数组下标
        while (left<=right){
            int mid = left+(right-left)/2;
            int count = 0;
            for (int num : nums) { // 统计不大于目标数字的元素个数
                if(num<=mid) count++;
            }
            if(count>mid) // 个数大于目标数字说明重复数字不大于目标数字
                right = mid - 1; // 数字减小
            else // 重复数字比目标数字大
                left = mid + 1; // 数字增大
        }
        return left;
    }
}
