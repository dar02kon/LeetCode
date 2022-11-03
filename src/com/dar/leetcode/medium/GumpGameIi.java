package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 跳跃游戏 II https://leetcode.cn/problems/jump-game-ii/description/
 * @create :2022-11-03 17:39:00
 */
public class GumpGameIi {
    public static void main(String[] args) {
        int[] nums = {1,2,1,1,1};
        int jump = new GumpGameIi().jump2(nums);
        System.out.println(jump);
    }
    /**
     * 从左往右进行搜索，在可抵达的位置上尽可能的走的远一些
     * <p>
     *     对于数组 [2, 3, 1, 2, 4, 2, 3]
     *        下标  0  1  2  3  4  5  6
     *     在下标为0的位置，我们可以跳两格，下标1与下标2
     *     由于下标1对应的数值为3，跳的更远，所以我们选则从下标0跳到下标1
     *     从下标1我们最远可以跳到下标4，由于下标4对应的数值为4，跳的更远，所以我们选择从下标1跳到下标4
     *     从下标4可以直接跳到终点
     * </p>
     * 在确定可以达到的位置后就需要确定到哪个位置下次跳的更远
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if(nums.length==1){//为1则直接返回
            return 0;
        }
        int i = 0;//记录起跳下标
        int count = 0;//记录次数
        int right = nums[0];//记录从起跳位置可达的最远边界
        while (i<nums.length){//遍历
            count++;
            int num = nums[i];
            if(right>=nums.length-1){//可到达终点
                return count;
            }
            int max = num+i ;//记录最远距离
            for (int j = i+1; j <= right; j++) {//寻找最远距离
                if(j+nums[j]>num+i){
                    max = Math.max(max,j+nums[j]);
                }
            }
            i = num+i;//更新起点位置
            right = max;//更新可达的最远边界
        }
        return count;
    }

    /**
     * 优化一下上面的代码
     * 在上面寻找最远起跳距离时，通过不断更新起点与可达的最远边界来进行循环，最终发现其实每一个元素都需要进行计算
     * 所以可以将上面的代码用一个循环来替代
     * 同样，我们需要记录起点位置（也可以说是边界），每次抵达起点位置，计数器都需要+1
     * 设置一个变量来记录最远可抵达的下标，遍历到起点位置后需要更新起点位置
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int count = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < nums.length-1; i++) {//i不需要抵达nums.length-1，我们只需要知道从哪一点可以跳到这
            max = Math.max(max,nums[i]+i);//这里记录的是最远抵达的点（下标），不是可以跳多少距离
            if(i==right){
                count++;
                right = max;
            }
        }
        return count;
    }
}
