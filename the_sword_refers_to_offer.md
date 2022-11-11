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

## 剑指 Offer 05. 替换空格

### 题目描述

[原题链接](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ReplaceSpaces.java)

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

 

**示例 1：**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

 

**限制：**

```
0 <= s 的长度 <= 10000
```

### 题解

#### 使用数组存储

直接的想法就是遍历字符串中的每一个字符，如果是空格就往数组中添加三个字符，即"%20"；如果不为空格则添加该字符。

最坏的情况下字符串全是空格，所以我们申请的空间必须为`3*s.length()`，即三倍字符串长度，虽然可能会造成空间浪费，但是Java并不支持动态申请空间，而且字符串String底层使用的是final修饰的char/byte数组（如果允许修改那为什么不直接对字符串进行扩容呢）

```java
 public String replaceSpace(String s) {
        char[] result = new char[s.length()*3];//初始化
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c==' '){
                result[index++] = '%';
                result[index++] = '2';
                result[index++] = '0';
            } else {
                result[index++] = c;
            }
        }
        return new String(result,0,index);
    }
```

**复杂度分析：**

时间复杂度 O(n)：遍历了字符串一边

空间复杂度 O(n)：使用了大小为`s.length()*3`的数组

#### 使用StringBuilder

直接使用`s.length()*3`大小的数组可能会造成空间的浪费，我们可以使用StringBuffer来进行字符串的拼接，StringBuffer同样也是使用数组来保存数据，与String不同的是它允许修改，并且空间不够时会进行“扩容”（其实还是重新初始化一个数组，不过调用的是本地方法，速度可能快一些），所以我们可以尝试初始化一个合适大小的StringBuffer对象，尽量不需要“扩容”，这个大小需要对数据进行分析。

```java
    public String replaceSpace2(String s) {
        StringBuilder result = new StringBuilder(s.length());//初始化，最初的大小可以根据数据来进行估算以减少重新申请空间的次数
        for (int i = 0; i < s.length() ; i++) {
            if(s.charAt(i)==' '){
                result.append("%20");
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
```

**复杂度分析：**

时间复杂度 O(n)：遍历了字符串一边，“扩容”的时间可以忽略

空间复杂度 O(n)：最少使用`s.length()`大小的空间，最多使用`s.length()*3`大小的空间，“扩容”可能会导致使用的空间翻倍。

## 剑指 Offer 06. 从尾到头打印链表

### 题目描述

[原题链接](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PrintALinkedListFromEndToEnd.java)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```

 

**限制：**

```
0 <= 链表长度 <= 10000
```

### 题解

#### 二次遍历

逆向输出链表，第一想法就是先获取链表长度，根据长度初始化数组，然后再遍历链表从后往前来填充数组

```java
    public int[] reversePrint(ListNode head) {
        int len = 0;//记录链表长度，同样也是填充数组时的下标
        ListNode p = head;
        while (p!=null){//获取链表长度
            len ++;
            p = p.next;
        }
        int[] result = new int[len];
        p = head;
        len = 0;
        while (p!=null){//填充数组
            result[len++] = p.val;
            p = p.next;
        }
        return result;
    }
}
```

**复杂度分析：**

时间复杂度 O(n)：遍历两次链表

空间复杂度 O(n)：保存结果的数组，这个是无法避免的，如果不考虑返回结果的占用空间，空间复杂度就是O(1)

## 剑指 Offer 07. 重建二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/RebuildTheBinaryTree.java)

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

**示例 1:**

![img](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
```

**示例 2:**

```
Input: preorder = [-1], inorder = [-1]
Output: [-1]
```

 

**限制：**

```
0 <= 节点个数 <= 5000
```



### 题解

#### 递归遍历

**先序遍历：**

* 访问根节点
* 先序遍历左子树
* 先序遍历右子树

**中序遍历：**

* 中序遍历左子树
* 访问根节点
* 中序遍历右子树

所以我们可以用先序序列来切分中序序列

对于先序序列`[3,9,20,15,7]`，中序序列`[9,3,15,20,7]`，先序序列的第一元素3即为根节点可以将中序序列分为 `[左子树,3,右子树]`，对于先序序列后面的元素同样可以将中序序列对应部分的左子树或右子树再次分割

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-10_20-12-47.png)

只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果寻找需要的根节点。

```java
    /**
     * 递归构建
     * @param preorder 前序
     * @param inorder 中序
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode();
        addNode2(head, preorder, inorder, 0, 0, preorder.length - 1);
        return head;
    }


    /**
     * 递归函数
     * @param head 根节点
     * @param preorder 前序序列
     * @param inorder 中序序列
     * @param index 前序序列下标
     * @param start 对应子树开始位置的下标（中序）
     * @param end 对应子树结束位置的下标（中序）
     */
    public void addNode(TreeNode head, int[] preorder, int[] inorder, int index, int start, int end) {
        for (int i = index; i < preorder.length; i++) {
            for (int j = start; j <= end; j++) {//寻找对应的根节点
                if (inorder[j] == preorder[i]) {
                    head.val = (preorder[i]);//添加节点
                    if (i + 1 == preorder.length) {//前序序列已经抵达边界
                        return;
                    }
                    if (start <= j - 1) {
                        head.left = new TreeNode();//添加左节点
                        addNode(head.left, preorder, inorder, i + 1, start, j - 1);//递归构建左子树
                    }
                    if (j + 1 <= end) {
                        head.right = new TreeNode();//添加右节点
                        addNode(head.right, preorder, inorder, i + 1, j + 1, end);//递归构建右子树
                    }
                    return;
                }
            }
        }
    }
```

上面这种写法当然可以找到结果，但是在搜索根节点时需要用到两重循环，一次是从前序序列中找我们需要的根节点，一次是根据找到的根节点在中序序列中进行定位，对于后者我们可以实现利用hash表存储中序序列来做到快速定位根节点所在位置，对于寻找我们需要的根节点我们可以利用已知信息以及前序序列的特点直接定位，

右子树：下一次的根节点下标 = 上一次根节点的下标+其左子树节点数+1，访问根节点后会先去访问左子树，左子树访问后再去访问右子树根节点

左子树：下一次的根节点下标 = 上一次根节点的下标+1，访问根节点后马上就会去访问其左子树的根节点（如果有的话）

```java
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {//创建中序序列hash表
            map.put(inorder[i], i);
        }
        TreeNode head = new TreeNode();
        addNode2(head, preorder, inorder, 0, 0, preorder.length - 1);
        return head;
    }
    
    public void addNode2(TreeNode head, int[] preorder, int[] inorder, int index, int start, int end) {
        if (index == preorder.length) {
            return;
        }
        Integer j = map.get(preorder[index]);//定位根节点位置
        head.val = (preorder[index]);//添加根节点的值
        if (start <= j - 1) {
            head.left = new TreeNode();
            addNode2(head.left, preorder, inorder, index + 1, start, j - 1);//递归创建左子树
        }
        if (j + 1 <= end) {
            head.right = new TreeNode();
            addNode2(head.right, preorder, inorder, index + j - start + 1, j + 1, end);//递归创建右子树
        }
    }
```

**复杂度分析：**

时间复杂度 O(n)：n为节点个数

空间复杂度 O(n)：除去保存结果需要的 O(n)空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h<n，所以总空间复杂度为 O(n)。

#### 迭代

对于前序遍历中的任意两个连续节点 u 和 v，根据前序遍历的流程，我们可以知道 u和 v 只有两种可能的关系：

* v 是 u 的左儿子。这是因为在遍历到 u 之后，下一个遍历的节点就是 u 的左儿子，即 v；
* u 没有左儿子，并且 v 是 u 的某个祖先节点（或者 u 本身）的右儿子。如果 u 没有左儿子，那么下一个遍历的节点就是 u 的右儿子。如果 u 没有右儿子，我们就会向上回溯，直到遇到第一个有右儿子（且 u 不在它的右儿子的子树中）的节点 p，则v就是p的右儿子

**算法流程：**

* 我们用一个栈和一个指针辅助进行二叉树的构造。初始时栈中存放了根节点（前序遍历的第一个节点），指针指向中序遍历的第一个节点；
* 我们依次枚举前序遍历中除了第一个节点以外的每个节点。如果 index 恰好指向栈顶节点，那么我们不断地弹出栈顶节点并向右移动 index，并将当前节点作为最后一个弹出的节点的右儿子；如果 index 和栈顶节点不同，我们将当前节点作为栈顶节点的左儿子；
* 无论是哪一种情况，我们最后都将当前的节点入栈。

```java
    /**
     * 迭代构建
     * @param preorder 前序
     * @param inorder 后序
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();//创建栈
        stack.push(root);//添加根节点
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {//遍历前序（不包含根节点）
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {//中序序列当前节点与栈顶节点不同
                node.left = new TreeNode(preorderVal);//添加为栈顶节点的左儿子
                stack.push(node.left);
            } else {//相同
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {//找父亲
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);//添加为右儿子
                stack.push(node.right);
            }
        }
        return root;
    }

```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是树中的节点个数。

空间复杂度：O(n)，除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(h)（其中 h 是树的高度）的空间存储栈。这里 h<n，所以（在最坏情况下）总空间复杂度为 O(n)

## 剑指 Offer 09. 用两个栈实现队列

### 题目描述

[原题链接](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheQueueIsImplementedWithTwoStacks.java)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

 

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
[[],[3],[],[],[]]
输出：[null,null,3,-1,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- 最多会对` appendTail、deleteHead `进行` 10000` 次调用

### 题解

#### 双栈

使用栈来模拟队列时，每次取队头元素都是在对栈地元素做处理，所以我们可以用另一个栈来存储倒置的元素来达到先进先出

具体做法就是使用两个栈IN与OUT，使用IN来添加元素，OUT用于取元素

我们需要将IN中的元素倒置存储在OUT中来达到先进先出，选择在OUT为空时将IN中的元素从栈顶依次取出存入OUT（如果IN不为空的话），如果OUT中存在元素时添加IN中的元素则会覆盖之前的结果

```java
/**
 * 使用两个栈，一个输出栈，一个输入栈
 */
class CQueue1 {
    private final Stack<Integer> stack;//输出栈
    private final Stack<Integer> target;//输入栈

    public CQueue1() {
        this.stack = new Stack<>();
        this.target = new Stack<>();
    }

    public void appendTail(int value) {
        target.push(value);//添加元素
    }

    public int deleteHead() {
        if (stack.empty()) {//输出栈为空
            if (target.isEmpty()) {//输入栈为空
                return -1;
            }
            while (!target.isEmpty()) {//将输入栈中元素依次取出存入输出栈中
                stack.push(target.pop());
            }
        }
        return stack.pop();//弹出栈顶
    }
}
```

**复杂度分析：**

时间复杂度 ：appendTail 为 O(1)，deleteHead 为均摊 O(1)。对于每个元素，至多入栈和出栈各两次，故均摊复杂度为 O(1)。

空间复杂度 O(n)：其中 n 是操作总数。对于有 n 次 appendTail 操作的情况，队列中会有 n 个元素，故空间复杂度为 O(n)。

#### 《一个栈》

Java中栈的底层是使用数组进行存储的，所以存在一些额外的操作，如删除数组第一个元素并返回，删除第一个元素的代码也很有意思

```java
    public synchronized E remove(int index) {
        modCount++;
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = elementData(index);

        int numMoved = elementCount - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--elementCount] = null; // Let gc do its work

        return oldValue;
    }
```

直观来看删除第一个元素的时间复杂度为O(1)

```java
/**
 * 一个栈（数组）
 */
class CQueue2 {
    private final Stack<Integer> stack;

    public CQueue2() {
        this.stack = new Stack<>();
    }

    public void appendTail(int value) {
        stack.push(value);
    }

    public int deleteHead() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.remove(0);
    }
}
```

**复杂度分析：**

时间复杂度 ：appendTail 为 O(1)，deleteHead 基本为 O(1)。

空间复杂度 O(n)：需要用一个栈来保存元素，n为栈的元素个数。
