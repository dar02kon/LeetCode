package com.dar.enterprise.Alibaba;

/**
 * @author :wx
 * @description : 215. 数组中的第K个最大元素 https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * @create :2023-06-25 19:48:00
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {

    }

    /**
     * 堆排序
     */
    private static class HeapSort {
        public int findKthLargest(int[] nums, int k) {
            heapSort(nums, k);
            return nums[0];
        }
        int len;

        /**
         * 堆排序，将最大值从堆顶移除，移k-1次，第k次即为数组中的第k个最大元素
         */
        private void heapSort(int[] nums, int k) {
            len = nums.length;
            built(nums);
            for (int i = len - 1; i >= 0 && k > 1; i--, k--) {
                swap(nums, 0, i);
                len -= 1;
                maintain(nums, 0);
            }
        }

        /**
         * 从下往上构建堆
         */
        private void built(int[] nums) {
            // 从第一个非叶子节点开始创建
            for (int i = len / 2 - 1; i >= 0; i--) {
                maintain(nums, i);
            }
        }

        /**
         * 维护堆
         */
        private void maintain(int[] nums, int i) {
            int pos = i;
            // 左节点
            int left = i * 2 + 1;
            // 右节点
            int right = i * 2 + 2;
            // 左节点大，往上移到
            if (left < len && nums[pos] < nums[left]) {
                pos = left;
            }
            // 右节点大，往上移到
            if (right < len && nums[pos] < nums[right]) {
                pos = right;
            }
            // 节点移到可能导致之前的结构发生改变，需要重新维护
            if (pos != i) {
                swap(nums, pos, i);
                maintain(nums, pos);
            }
        }

        /**
         * 交换数组元素
         */
        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    /**
     * 快排
     */
    private static class QuickSort {
        public int findKthLargest(int[] nums, int k) {
            quickSort(nums, 0, nums.length - 1, k - 1);
            return nums[k - 1];
        }

        /**
         * 快速排序（分治算法）
         */
        private void quickSort(int[] nums, int low, int high, int k) {
            if (low < high) {
                int pos = partition(nums, low, high);
                // 根据k的位置来选取区域
                if (pos > k) {
                    quickSort(nums, low, pos - 1, k);
                } else {
                    quickSort(nums, pos + 1, high, k);
                }
            }
        }

        /**
         * 分区，以nums[high]为边界，右边均小于nums[high]，左边均大于等于
         */
        private int partition(int[] nums, int low, int high) {
            int center = nums[high];
            // pos前的元素均大于等于nums[high]
            int pos = low;
            for (int i = low; i < high; i++) {
                // 将大于等于nums[high]的元素往前移
                if (nums[i] >= center) {
                    swap(nums, i, pos);
                    pos++;
                }
            }
            // 移动边界
            swap(nums, pos, high);
            return pos;
        }

        /**
         * 交换元素
         */
        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
