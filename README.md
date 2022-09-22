# LeetCode刷题记录

# 算法

## 二分查找

二分查找也称折半查找（Binary Search），它是一种效率较高的查找方法。但是，折半查找要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列。

首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，如果两者相等，则查找成功；否则利用中间位置记录将表分成前、后两个子表，如果中间位置记录的关键字大于查找关键字，则进一步查找前一子表，否则进一步查找后一子表。重复以上过程，直到找到满足条件的记录，使查找成功，或直到子表不存在为止，此时查找不成功。

```java

    /**
     * 从数组nums中寻找target，找到返回true，否则返回false
     */
    public boolean search(int[] nums, int target) {
       int left = 0;
       int right = nums.length;
       while (left<=right){
           //考虑到可能发生的整型溢出，使用 left + (right - left)/2 取mid更安全一点。
           int mid = left + (right - left) / 2;
           if(target == nums[mid]){
               return true;
           } else if(target < nums[mid]){
               right = mid - 1;
           } else {
               left = mid + 1;
           }
       }
       return false;
    }
```

相关题目：

[搜索插入位置](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/SearchInsertPosition.java)
