# LeetCode刷题记录

**刷刷题，做做笔记只是为了我自己身心愉悦**

## 已刷算法题

### 简单

* [二进制求和](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/AddBinary.java)
* [最后一个单词的长度](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/LengthOfLastWord.java)
* [最长公共前缀](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/LongestCommonPrefix.java)
* [合并两个有序链表](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/MergeTwoSortedLists.java)
* [回文数](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/PalindromeNumber.java)
* [加一](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/PlusOne.java)
* [删除有序数组中的重复项](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/RemoveDuplicatesFromSortedArray.java)
* [移除元素](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/RemoveElement.java)
* [罗马数字转整数](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/RomanToInteger.java)
* [搜索插入位置](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/SearchInsertPosition.java)
* [有效的括号](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/ValidParentheses.java)
* [x 的平方根](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/Sqrtx.java)

### 中等

### 困难

## 算法

### 双指针

设置两个指针（变量）不断进行单向移动来解决问题的算法。

原本两个指针是有 n<sup>2</sup>种组合，因此时间复杂度是 O(n<sup>2</sup>) 。
而双指针算法就是运用单调性使得指针只能单向移动，因此总的时间复杂度只有 O(n)。

之所以双指针可以实现 O(n) 的时间复杂度是因为指针只能单向移动，没有指针的回溯，而且每一步都会有指针移动。

而朴素的 O(n<sup>2</sup>) 算法的问题就在于指针经常**回溯到之前的位置**。

相关题目：

* [移除元素](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/RemoveElement.java)
* [删除有序数组中的重复项](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/RemoveDuplicatesFromSortedArray.java)

### 二分查找

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

* [搜索插入位置](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/SearchInsertPosition.java)
* [x 的平方根](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/Sqrtx.java)
