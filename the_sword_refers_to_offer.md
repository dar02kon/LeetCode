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

## 剑指 Offer 10- I. 斐波那契数列

### 题目描述

[原题链接](https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheFibonacciSequence.java)

写一个函数，输入 `n` ，求斐波那契（Fibonacci）数列的第 `n` 项（即 `F(N)`）。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

**示例 1：**

```
输入：n = 2
输出：1
```

**示例 2：**

```
输入：n = 5
输出：5
```

 

**提示：**

- `0 <= n <= 100`

### 题解

#### 常规思路

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。根据这个规律进行循环即可，但因为计算结果可能比较大，使用int类型会越界，所以结果需要取模 1e9+7（题目要求）

```java
    public int fib(int n) {
        if (n <= 1)
            return n;
        int a = 0;
        int b = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;//对结果取模
            a = b;
            b = sum;
        }
        return b;
    }
```

**复杂度分析：**

时间复杂度O(n) 

空间复杂度 O(1)

#### 矩阵快速幂

我们可以使用快速幂来模拟乘法运算，相比一个数慢慢加，快速幂要快很多。

同样我们可以使用矩阵快速幂的方法来求解斐波那契数以达到降低时间复杂度的目的

对于

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-31-31.png)

我们需要找到一个矩阵M时一下等式成立

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-31-39.png)

根据定义设M为（对M求幂的结果矩阵应与M的阶数相同）

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-30-14.png)

求解

```
a*F(n) + b*F(n-1) = F(n+1)
c*F(n) + d*F(n-1) = F(n)
```

解得M为

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-33-07.png)

所以有

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-36-01.png)

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-39-22.png)

只要我们能快速计算矩阵 M 的 n 次幂，就可以得到 F(n) 的值。如果直接求取 M的n次方 ，时间复杂度是 O(n)，可以定义矩阵乘法，然后用快速幂算法来加速这里 M的n次幂的求取。因为我们只需要知道F(n)的值，所以求M的n-1次方即可。

因为M求幂后还是二阶矩阵，并且有

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-12_20-47-28.png)

所以M的n-1次方后得到的二阶矩阵，其`(0,0)`位置即为F(n)

```java
    static final int MOD = (int) (1e9) + 7;

    public int fib2(int n) {
        if (n <= 1)
            return n;
        int[][] num = {{1, 1}, {1, 0}};
        int[][] pow = pow(num, n - 1);
        return pow[0][0];
    }

    // 矩阵快速幂算法
    public int[][] pow(int[][] a, int n) {
        int[][] sum = {{1, 0}, {1, 0}};//初始化，相当于普通数字1
        while (n > 0) {
            if ((n & 1) == 1) {
                sum = multiply(sum, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return sum;
    }

    // 矩阵乘法
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = (int) ((((long) a[i][0] * b[0][j]) + (long) a[i][1] * b[1][j]) % MOD);//a的一行乘以b的一列
            }
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度O(log⁡*n*) 

空间复杂度 O(1)：使用的矩阵大小都是确定的，只有四个元素

## 剑指 Offer 10- II. 青蛙跳台阶问题

### 题目描述

[原题链接](https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/FrogJumpingSteps.java)

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 `n` 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：2
```

**示例 2：**

```
输入：n = 7
输出：21
```

**示例 3：**

```
输入：n = 0
输出：1
```

**提示：**

- `0 <= n <= 100`

### 题解

#### 动态规划

我们从后往前看，青蛙如果想要跳到第三个台阶，只有两种方式：从第一个台阶跳到第三个台阶或者从第二个台阶跳到第三台阶（一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。）

跳到第三个台阶的跳法与跳到第一个台阶的跳法以及跳到第二个台阶的跳法有关，即`F(3) = F(2)+F(1)`，推广至n，即`F(n)=F(n-1)+F(n-2) n>=2`，递推公式与斐波那契数列相同，不过F(0) = 1。

```java
    static final int MOD = 1000000007;
    public int numWays(int n) {
        if (n <= 1)
            return 1;
        int a = 1;
        int b = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % MOD;//取余
            a = b;
            b = sum;
        }
        return b;
    }
```

**复杂度分析：**

时间复杂度O(n) 

空间复杂度 O(1)

#### 矩阵快速幂

与斐波那契数列的思路相同，使用快速幂算法来计算M矩阵的n-1次方就能得到结果矩阵N，`F(n) =(N[0,0]+N[0,1])%MOD`，因为F(0) = 1。

```java
    static final int MOD = 1000000007;

    /**
     * 矩阵快速幂
     * @param n
     * @return
     */
    public int numWays2(int n) {
        int[][] nums = {{1, 1}, {1, 0}};
        int[][] pow = pow(nums, n - 1);
        System.out.println(Arrays.deepToString(pow));
        return (pow[0][0] + pow[0][1]) % MOD;
    }

    //矩阵快速幂
    public int[][] pow(int[][] nums, int n) {
        int[][] sum = {{1, 0}, {0, 1}};//初始化，使用{{1,0},{1,0}}也可以，结果的第一行是相同的
        while (n > 0) {
            if ((n & 1) == 1) {
                sum = multiply(sum, nums);
            }
            nums = multiply(nums, nums);
            n >>= 1;
        }
        return sum;
    }
    //矩阵乘法
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                c[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }
```

**复杂度分析：**

时间复杂度O(log⁡*n*) 

空间复杂度 O(1)：使用的矩阵大小都是确定的，只有四个元素

## 剑指 Offer 11. 旋转数组的最小数字

### 题目描述

[原题链接](https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/RotateTheSmallestNumberOfAnArray.java)

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

给你一个可能存在 **重复** 元素值的数组 `numbers` ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的**最小元素**。例如，数组 `[3,4,5,1,2]` 为 `[1,2,3,4,5]` 的一次旋转，该数组的最小值为 1。 

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` 旋转一次 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

 

**示例 1：**

```
输入：numbers = [3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：numbers = [2,2,2,0,1]
输出：0
```

 

**提示：**

- `n == numbers.length`
- `1 <= n <= 5000`
- `-5000 <= numbers[i] <= 5000`
- `numbers` 原来是一个升序排序的数组，并进行了 `1` 至 `n` 次旋转

### 题解

#### 二分查找

对于旋转后的数组可以分为两种基本情况：

一是没有旋转（测试用例中就有这种情况）

二是将前面n位移到后面

例如[1,2,3,4,5,6]旋转后的结果可为[1,2,3,4,5,6]与[4,5,6,1,2,3]等

我们可以以最后一个元素为基准，设为right，在最小值的右侧的一定都小于或等于right；在最小值的左侧都大于或者等于right

如果我们以第一个元素为基准，设为left，则可能出现冲突：对于[4,5,6,1,2,3]，最小值右侧都是小于或者等于left；但对于[1,2,3,4,5,6]，最小值右侧都是大于或者等于left。所以会出现冲突

最终，我们以靠近右侧的元素为基准进行二分查找

设左指针为left，右指针为right，中间点为mid = (left + right) / 2

进行值比较存在三种情况：

* `numbers[mid]<numbers[right]`，则可以确定右侧区域都是大于最小值的，可以舍弃，right = mid（这个mid可能对应最小值，不能盲目mid-1）
* `numbers[mid]>numbers[right]`，则可以确定左侧区域都是大于最小值的，可以舍弃，left = mid + 1（这个mid肯定不会对应最小值，因为都已经有大于的值了）
* `numbers[mid]==numbers[right]`，这个我们无法判断舍弃哪个区域，所以就都不舍弃了，直接将右指针左移一位，因为`numbers[right]`存在替代品`numbers[mid]`

最终左指针与右指针指向同一个位置，返回其对应的值即可

```java
    public int minArray(int[] numbers) {
        int left = 0;//左指针
        int right = numbers.length - 1;//右指针
        while (left < right) {
            int mid = left + (right - left) / 2;//中间点
            if (numbers[mid] < numbers[right]) {
                right = mid;//mid可能就是需要找的位置
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;//这个mid肯定不是要找的位置，因为numbers[mid]>numbers[right]
            } else {
                right--;//左移一位继续试探
            }
        }
        return numbers[left];//返回numbers[right]也可以
    }
```

**复杂度分析：**

时间复杂度：平均时间复杂度为 O(logn)，其中 n 是数组 numbers 的长度。如果数组是随机生成的，那么数组中包含相同元素的概率很低，在二分查找的过程中，大部分情况都会忽略一半的区间。而在最坏情况下，如果数组中的元素完全相同，那么 while 循环就需要执行 n 次，每次忽略区间的右端点，时间复杂度为 O(n)。

空间复杂度：O(1)。

## 剑指 Offer 12. 矩阵中的路径

### 题目描述

[原题链接](https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PathInAMatrix.java)

给定一个 `m x n` 二维字符网格 `board` 和一个字符串单词 `word` 。如果 `word` 存在于网格中，返回 `true` ；否则，返回 `false` 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。

![img](https://assets.leetcode.com/uploads/2020/11/04/word2.jpg)

 

**示例 1：**

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

**示例 2：**

```
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```

 

**提示：**

- `m == board.length`
- `n = board[i].length`
- `1 <= m, n <= 6`
- `1 <= word.length <= 15`
- `board `和` word `仅由大小写英文字母组成

### 题解

#### 回溯

以每一个位置为起点进行递归回溯

如以`board[i][j]`为起点，首先判断`board[i][j]`与`word.charAt(0)`是否相同，如果相同则继续向前遍历。理论上`board[i][j]`的下一个位置有四处，分别为`board[i-1][j]`、`board[i][j-1]`、`board[i+1][j]`、`board[i][j+1]`，可以按顺序判断与`word.charAt(1)`是否相同，如果相同则继续递归，不同返回递归下一处，四处均不符合要求则回溯到上一步去递归上一步的下一处

由于同一个单元格内的字母不允许被重复使用，所以需要用一个数组来标记哪些位置访问过，访问过的不能再次访问，并且在回溯后需要清除标记

```java
    boolean[][] visited;//标记数组
    boolean flag = false;//最终结果
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                route(board, i, j, word, 0);//以每一个元素为起点去深搜
            }
        }
        return flag;
    }

    public void route(char[][] board, int x, int y, String word, int index) {
        if (index == word.length()) {//找到了字符串
            flag = true;
            return;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(index)) {//不符合要求的情况
            return;
        }
        visited[x][y] = true;//标记访问
        route(board, x + 1, y, word, index + 1);//下移，继续递归
        route(board, x, y + 1, word, index + 1);//右移，继续递归
        route(board, x - 1, y, word, index + 1);//上移，继续递归
        route(board, x, y - 1, word, index + 1);//左移，继续递归
        visited[x][y] = false;//清除标记
    }
```

**复杂度分析：**

时间复杂度：一个非常宽松的上界为 O(MN⋅3^L)，其中M, N为网格的长度与宽度，L 为字符串 word 的长度。在每次调用函数route时，除了第一次可以进入4个分支以外，其余时间我们最多会进入3个分支（因为每个位置只能使用一次，所以走过来的分支没法走回去）。由于单词长为 L，故 `route(board, i, j, word, 0)` 的时间复杂度为 O(3^L)，而我们要执行O(MN) 次检查。然而，由于剪枝的存在，我们在遇到不匹配或已访问的字符时会提前退出，终止递归流程。因此，实际的时间复杂度会远远小于 O(MN⋅3^L)

空间复杂度：O(MN)。我们额外开辟了O(MN) 的 visited 数组，同时栈的深度最大为 O(min(L,MN))。

## 剑指 Offer 14- I. 剪绳子

### 题目描述

[原题链接](https://leetcode.cn/problems/jian-sheng-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/CutTheRope.java)

给你一根长度为 `n` 的绳子，请把绳子剪成整数长度的 `m` 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 `k[0],k[1]...k[m-1]` 。请问 `k[0]*k[1]*...*k[m-1]` 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

**示例 1：**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例 2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

**提示：**

- `2 <= n <= 58`

### 题解

#### 动态规划

对于5：

* 我们可以把它分为2和3，此时乘积为6；
* 我们不会把2再分，因为2再分后的乘积不会大于2；
* 我们也不会把3再分，因为3再分后的成绩不会大于3；

所以，对于5，分割后最大乘积就为2*3=6

尝试将数字5推广至n

* 我们可以把它分为 i 与 n-i ；
* 我们需要判断 i 是否要再分，再分的前提是 i 再分后的乘积比 i 大
* 我们需要判断 n-i 是否要再分，再分的前提是 n-i 再分后的乘积比 n-i 大

可以得出一点判断是否再分需要借助前面出现的结果，即判断 i 与 n-i 是否要再分前提是 i 与 n-i 在之前就已经分割过，并且我们知道结果，同时 i 的取值范围应该为 [1,n)，其实可以缩小到[1,n/2]，因为 i 与 n-i 是可以交换位置的

数字应该数组dp来记录最大乘积，dp[i]表示 i 分割后的最大乘积数，i从2开始有效(>=2才可分)，初始化dp[0]=dp[1]=0

```java
    public int cuttingRope(int n) {
        int[] dp = new int[n+1];//记录信息
        for (int i = 2; i <= n ; i++) {
            int max = 0;
            for (int j = 1; j <= i/2; j++) {//将i分成不同的两部分
                max = Math.max(max,Math.max(j*(i-j),j*dp[i-j]));//与之前的信息进行比较，选取最大乘积
            }
            dp[i] = max;
        }
        return dp[n];
    }
```

**复杂度分析：**

时间复杂度：O(n^2)，其中 n 是给定的正整数。对于从 2 到 n 的每一个整数都要计算对应的 dp 值，计算一个整数对应的dp值需要 O(n/2)的时间复杂度，因此总时间复杂度最坏是 O(n^2)。

空间复杂度：O(n)，其中 n 是给定的正整数。创建一个数组 dp，其长度为 n+1

#### 数学

观察以2-10的最大乘积

```
2	1*1		1
3	1*2		2
4	2*2		4
5	2*3		6
6	3*3		9
7	2*2*3	12
8	2*3*3	18
9	3*3*3	27
10	2*2*3*3	36
```

可以发现在分解后乘积最大的数中3出现的最多，2和1最多出现两个而且1只会在数字2和数字3的拆分中出现。

所以对于数字n的拆分可以尝试先找出最多的3，并且当n>3后拆分结果中不能出现1，若出现1则拿出一个3合并为4（或2*2）

**归纳证明法**

* 第一步：证明最优的拆分方案中不会出现大于 4 的整数

  ```
  假设出现了大于 4 的整数 x，由于 2(x−2)>x 在 x>4 时恒成立，将 x 拆分成 2 和 x−2 可以增大乘积。因此最优的拆分方案中不会出现大于 4 的整数。
  ```

* 第二步：证明最优的拆分方案中可以不出现整数 4

  ```
  如果出现了整数 4，我们可以用 2×2代替，乘积不变。此时，我们可以知道，最优的拆分方案中只会出现 1，2 和 3。
  ```

* 第三步：证明当 n≥5 时，最优的拆分方案中不会出现整数 1

  ```
  当 n≥5 时，如果出现了整数 1，那么拆分中剩余的数的和为 n−1≥4，对应这至少两个整数。我们将其中任意一个整数 x 加上 1，乘积就会增大。因此最优的拆分方案中不会出现整数 1。
  此时，我们可以知道，当 n≥5 时，最优的拆分方案中只会出现 2 和 3。
  ```

* 第四步：证明当 n≥5 时，最优的拆分方案中 2 的个数不会超过 3 个

  ```
  如果出现了超过 3 个 2，那么将它们转换成 2 个 3，可以增大乘积，即3×3>2×2×2。
  ```

```java
    public int cuttingRope2(int n) {
        if(n < 4){
            return n - 1;
        }
        int res = 1;
        while(n > 4){
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

还有一种写法就是直接算出数字n最多可以由多少个3组成，直接对结果进行处理

```java
    public int cuttingRope3(int n) {
        if(n < 4){
            return n - 1;
        }
        int a = n/3;
        int b = n%3;
        if(b==1){
            return (int) (Math.pow(3,a-1)*4);
        } else if (b==2){
            return (int) (Math.pow(3,a)*b);
        } else {
            return (int) (Math.pow(3,a));
        }
    }
```

**复杂度分析：**

时间复杂度：O(1)。涉及到的操作包括计算商和余数，以及幂次运算，时间复杂度都是常数。

空间复杂度：O(1)。只需要使用常数复杂度的额外空间。

## 剑指 Offer 14- II. 剪绳子 II

### 题目描述

[原题链接](https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/CutTheRopeII.java)

给你一根长度为 `n` 的绳子，请把绳子剪成整数长度的 `m` 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 `k[0],k[1]...k[m - 1]` 。请问 `k[0]*k[1]*...*k[m - 1]` 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

**示例 1：**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例 2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

 

**提示：**

- `2 <= n <= 1000`

### 题解

#### 数学

这题思路与上面一题相同，不过需要做越界处理，所以用多态规划不太好弄，`j*dp[i-j]`数量级一下就上来了很难处理，所以采用第二种做法，即去寻找最多的3，最后结果不能直接调用`Math.pow()`来计算，需要一步一步做乘法处理并且对乘法结果进行取模

```java
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        long result = 1;//使用long型来做转换，防止取模前就越界
        while (n > 4) {
            result = result * 3 % 1000000007;//取模
            n -= 3;
        }
        return (int) (result * n % 1000000007);//最后结果也需要取模
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 15. 二进制中1的个数

### 题目描述

[原题链接](https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheNumberOfOnesInBinary.java)

编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 [汉明重量](http://en.wikipedia.org/wiki/Hamming_weight)).）。

 

**提示：**

- 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
- 在 Java 中，编译器使用 [二进制补码](https://baike.baidu.com/item/二进制补码/5295284) 记法来表示有符号整数。因此，在上面的 **示例 3** 中，输入表示有符号整数 `-3`。

 

**示例 1：**

```
输入：n = 11 (控制台输入 00000000000000000000000000001011)
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：n = 128 (控制台输入 00000000000000000000000010000000)
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

 

**提示：**

- 输入必须是长度为 `32` 的 **二进制串** 。

### 题解

#### 位运算

输入必须是长度为 `32` 的 **二进制串** ，所以我们可以循环判断二进制串中的每一位是否为1，当检查第 i 位时，我们可以让 n 与 2^i 进行与运算，当且仅当 n的第 i 位为 1 时，运算结果不为 0

```java
    public int hammingWeight1(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                sum++;
            }
        }
        return sum;
    }
```

如果想通过移动n，每次比较最后一位，则可以这么写

```java
    public int hammingWeight(int n) {
        int sum = 0;
        if (n >= 0) {
            while (n > 0) {
                if ((n & 1) == 1) {
                    sum++;
                }
                n >>= 1;
            }
            return sum;
        } else {
            while (n != -1) {
                if ((n & 1) == 0) {
                    sum++;
                }
                n >>= 1;
            }
            return 32 - sum;
        }
    }
```

当 n >= 0，统计1的个数，n==0 时终止；当 n < 0，统计 0 的个数，n==-1时终止

**复杂度分析：**

时间复杂度：O(n)，本题一共需要检查 32 位，所以n一直等于32

空间复杂度：O(1)

#### 位运算优化

上面两种写法都是循环比较进行判断的，有一些判断是无效的，即没有找到1，那有没有一种解法每循环一次就可以找到一个1？可以通过

`n &= (n-1) `来实现，`n & (n−1)`，其结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。不断循环这个运算直到n变为0，每循环一次就少一个1

```java
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n!=0){
            sum++;
            n &= (n-1);
        }
        return sum;
    }
```

时间复杂度：O(log⁡n)。循环次数等于 n 的二进制位中 1 的个数，最坏情况下 n 的二进制位全部为 1。

空间复杂度：O(1)，我们只需要常数的空间保存若干变量。
