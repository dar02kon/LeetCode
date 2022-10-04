package com.dar.leetcode.simple;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 多数元素 https://leetcode.cn/problems/majority-element/
 * @create :2022-10-04 18:23:00
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,2,2};
        System.out.println(new MajorityElement().majorityElement3(nums));
    }

    /**
     * 先对数组进行排序
     * 设置三个变量，一个记录出现次数，一个记录位置，一个记录当前出现最多的次数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int count = 1;
        int result = 0;
        for (int i = 0; i < nums.length ; i++) {
            if(i<nums.length-1&&nums[i]==nums[i+1]){
                count ++;
            } else {
                if(max<=count){//比较出现次数
                    result = i;
                    max = count;
                }
                count = 1;
            }
        }
        return nums[result];
    }

    /**
     * 注意题干条件
     * 多数元素是指在数组中出现次数 大于n/2（n为数组长度）的元素。
     * 所以排序之后我们返回nums[n/2]即可，
     * 因为多数元素至少要占整个数组的一半再加上1，nums[n/2]必然是多数元素
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * Boyer-Moore 投票算法
     * 我们维护一个候选众数 candidate 和它出现的次数 count。
     * 初始时 candidate 可以为任意值，count 为 0；
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，
     * 如果 count 的值为 0，我们先将 x 的值赋予 candidate，
     * 随后我们判断 x：
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     * 在遍历完成后，candidate 即为整个数组的众数。
     *
     * <p>
     *     证明（理解）：
     *     假设max是多数元素即在在数组中出现次数 大于n/2（n为数组长度）的元素
     *     我们只关注结果谁票数最多，这当然是max，因为我们提前知道他有一半以上的票，所以结果max会获胜，这也是我们想要的。
     *     假设我们提前不知道谁票数最多，想要通过选举的结果来判断，我们当然会选最终赢的人，因为只有票数最多才会赢
     *
     *     在选举之前就可以确定max已经占了一般以上的票数（因为有一般以上的人支持他）
     *     如果候选人不是max，则支持max一般以上的人都会投反对票（计数器为0时换人）
     *     如果max是候选人，则会有一般以上的人投他，即使中途出现过换人，他还是会重返舞台，赞成和反对会抵消，最终只会剩下支持他的人
     * </p>
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            if(candidate==num){
                count++;
            } else {
                if(count==0){
                    candidate = num;
                } else {
                    count --;
                }
            }
        }
        return candidate;
    }


}
