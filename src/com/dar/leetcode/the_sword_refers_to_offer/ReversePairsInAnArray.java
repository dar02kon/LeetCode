package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 51. 数组中的逆序对 https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-28 10:53:00
 */
public class ReversePairsInAnArray {

    public static void main(String[] args) {

    }

    int[] temp;

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        return mergeSort(0, nums.length - 1, nums);
    }

    public int mergeSort(int left, int right, int[] nums) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int count = mergeSort(left, mid, nums) + mergeSort(mid + 1, right, nums);
        System.arraycopy(nums, left, temp, left, right - left + 1);//初始化辅助数组
        int i = left, j = mid + 1;
        //合并两个数组
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {//第一个数组已经添加完成
                nums[k] = temp[j++];//只用添加第二个数组的元素
            } else if (j == right + 1 || temp[i] <= temp[j]) {//第二个数组添加完成或左指针指向的元素比右指针小，则添加第一个数组的元素
                nums[k] = temp[i++];
            } else {//出现左指针指向的值大于右指针指向的值
                nums[k] = temp[j++];//添加第二个数组的元素
                // 统计逆序对
                // 左指针指向的值以及其后的元素都比右指针指向的值大（两个数组各自已经排序好了），
                // 所以左指针指向的值以及其后的元素都可以与右指针指向的值构成逆序对
                count += mid - i + 1;
            }
        }
        return count;
    }
}
