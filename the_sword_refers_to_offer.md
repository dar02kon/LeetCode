# 剑指offer

## 数组中重复的数字

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ADuplicateNumberInAnArray.java)

找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**示例 1：**

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

 

**限制：**

```
2 <= n <= 100000
```

### 题解

#### 排序后遍历

对与给定的数组我们可以先将其排序后再挨个进行查找

```java
 public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);//排序
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {//挨个查找
                return nums[i];
            }
        }
        return -1;
  }
```

**复杂度分析：**

由于调用了`Arrays.sort()`函数，时间与空间复杂度不好计算，但有一点Java 7对整数重载使用双枢轴快速排序变量,实际上具有*O(n^2)*的最坏情况，空间复杂度也不止O(1)那么简单。

#### 使用hash表

可以使用hash表来快速添加数字以及判断数字是否已经出现，其`add()`方法与`contains()`方法时间复杂度均为O(1)

```java
public int findRepeatNumber2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>(nums.length - 1);//使用hash表来记录出现过的数字
        for (int num : nums) {//遍历
            if (hashSet.contains(num)) {//已经存在
                return num;
            }
            hashSet.add(num);//添加数字
        }
        return -1;
    }
```

**复杂度分析：**

时间复杂度 O(N)： 遍历数组为 O(N) ，HashSet 添加与查找元素皆为 O(1)。
空间复杂度 O(N)： HashSet 占用 O(N)大小的额外空间。

#### 映射（交换位置）

在题目描述中有这样一句：在一个长度为 n 的数组 nums 里的**所有数字都在 0～n-1 的范围内**。

所以我们可以将每一个数字映射到其对应的位置，即使nums[i] = i。当出现nums[nums[i]] = nums[i]即为出现重复，如nums[1]的值为3，我们需要将3放入nums[3]，但那个位置已经有了一个3即此时nums[3]==3==nums[1]，出现重复

对如将数字如何放入指定位置，我们可以采用原地交换直到此时的nums[i] = i

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-07_17-37-50.png)

```java
public int findRepeatNumber3(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {//该位置已经调整好，遍历下一个位置
                i++;
                continue;
            }
            if (nums[i] == nums[nums[i]]) {//出现冲突
                return nums[i];
            } else {//交换（不断交换至nums[i] == i）
                int record = nums[i];
                nums[i] = nums[record];
                nums[record] = record;
            }
        }
        return -1;
    }
```

**复杂度分析：**

时间复杂度 O(N)： 遍历数组需要O(N)，每轮遍历的判断和交换操作只需要O(1) 。
空间复杂度 O(1)： 使用常数复杂度的额外空间。

