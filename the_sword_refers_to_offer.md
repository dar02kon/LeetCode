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



## 剑指 Offer 04. 二维数组中的查找

### 题目描述

[原题链接](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/LookupInATwoDimensionalArray.java)

在一个 n * m 的二维数组中，每一行都按照从左到右 **非递减** 的顺序排序，每一列都按照从上到下 **非递减** 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

**示例:**

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

 

**限制：**

```
0 <= n <= 1000
0 <= m <= 1000
```

 

### 题解

#### 二分查找

根据题目描述可知，每一行，每一列都按照从左到右 **非递减** 的顺序排序。

**确定需要进行搜索的行**

可以先用二分查找对第一列进行搜索找到第一个小于或者等于目标值的下标，设为n

再利用二分查找对最后一列进行搜索寻找第一个大于或者等于目标值的下标，设为m

则如果m<=n，则说明有存在的行可能找到目标值

对于矩阵（查找5）

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

m = 0，n = 2，在0-2行可能存在目标值

**再对范围内的每一行进行二分查找**

同样去寻找第一个大于或者等于目标值的下标，如果这个下标对应的数恰好为目标值则直接返回true，否则缩短下一行查找范围，即右边界更换为这个下标，这个下标对应的下侧数值肯定不比它小，没必要再往右边寻找（越往右就越大）

同样对于矩阵（查找5）：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

第一行搜索结束后可以将下一行的右边界缩短到 2（第一行位于第3列的数字7是第一个大于或者等于目标值5的数字）

```java
 public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 寻找矩阵第一列第一个小于或者等于目标值的下标n
        // 从0-n行从首部来看可能存在目标值
        int left = -1;
        int right = matrix.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[mid][0] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int n = left;// 上界
        // 寻找矩阵最后一列第一个大于或者等于目标值的下标m
        // 从m-matrix.length-1行从尾部来看可能存在目标值
        left = 0;
        right = matrix.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (matrix[mid][matrix[0].length - 1] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int m = right;// 下界
        if (n < m) {// 不存在目标值
            return false;
        }
        // 以m为起始行依次二分查找至第n行
        // 寻找每一行行中第一个大于或者等于目标值的下标，
        // 若等于则返回true，大于缩小下次查找的右边界
        int len = matrix[0].length;//列数
        for (int i = m; i <= n; i++) {
            left = 0;
            right = len;
            while (left < right) {
                int mid = (left + right) / 2;
                if (matrix[i][mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (matrix[i][right] == target) {//找到目标值
                return true;
            }
            len = right;//缩小右边界
        }
        return false;
    }
```

**复杂度分析：**

时间复杂度 ： 对一行使用二分查找的时间复杂度为*O*(log*m*)，对多对n行进行查找，对一列使用二分查找的时间复杂度为*O*(log*n*)，需要对2列进行查找，综合一下最坏情况下的时间复杂度为 *nO*(log*m*) + *2O*(log*n*)，其中m为行数，n为列数。均摊下来时间复杂度还是比较乐观的。
空间复杂度 O(1)： 使用常数复杂度的额外空间。

#### 根据特点进行搜索

上面这种搜索仍然未充分利用矩阵的特性，每一次二分搜索差不多都是独立的，没有充分利用上一次搜索获得的有效信息，但同样要利用这些信息又需要额外编写代码来记录和使用这些信息（就是上面缩短右边界一样）

观察矩阵右上角那一点，可以发现如果将它的位置往左移动它会变小，往下移动会变大，类似于二叉搜索树。如果将这一点对应的数值与目标值进行比较，它只有三种行为，等于目标值返回true，大于目标值往左移动来减小数值（不能往上移动，因为我们就是从那来的），小于目标值往下移动来增大数值（同样不能往右移动）。

这样所走的每一步都是从前面一点一滴积累过来的，充分利用了已知信息。

```java
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        int row = 0;//对应行数
        int column = matrix[0].length-1;//对应列数
        while (row<matrix.length&&column>=0){
            if(matrix[row][column]==target){
                return true;
            }
            if(matrix[row][column]>target){
                column--;//列数减一，向左移动
            } else {
                row++;//行数加一，向下移动
            }
        }
        return false;
    }
```

**复杂度分析：**

时间复杂度：O(n+m)。在搜索的过程中，如果我们没有找到 target，那么我们要么将 column 减少 1，要么将 row 增加 1。由于 (row,column) 的初始值分别为 (0,m−1)，因此 column 最多能被减少 m 次，row 最多能被增加 n 次，总搜索次数为 n+m。在这之后，row 和 column 就会超出矩阵的边界。

空间复杂度：O(1)。

