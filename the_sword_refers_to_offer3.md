## 剑指 Offer 37. 序列化二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/SerializeTheBinaryTree.java)

请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

**提示：**输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 [LeetCode 序列化二叉树的格式](https://support.leetcode-cn.com/hc/kb/article/1567641/)。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

**示例：**

![img](https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg)

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```

 

### 题解

#### 层次遍历

*获取序列*

层次遍历二叉树，使用`StringBuilder`来保存结果，节点值之间用空格隔开，null 用 ! 来代替。使用队列来存储节点辅助遍历。

若从队列取出的节点为空则添加 !，不为空添加其左右节点至队列

最终返回`StringBuilder`对应的字符串

```java
    public String serialize(TreeNode root) {
        if (root == null) {//树为空返回null
            return null;
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();//辅助队列
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node!=null){
                result.append(node.val).append(" ");//保存节点值
                //添加其左右节点
                queue.add(node.left);
                queue.add(node.right);
            } else {//添加空标志
                result.append("! ");
            }
        }
        return result.toString();
    }

```

**复杂度分析：**

时间复杂度：O(N)

空间复杂度：O(N) 

*解析序列*

使用空格将字符串转成字符数组，添加数组第一个元素至队列。遍历数组，从队列中取出一个元素，不为空则添加其左右节点，为空则跳过，直接移动数组指针。

```java
    public TreeNode deserialize(String data) {
        if (data == null) {//序列为空返回null
            return null;
        }

        String[] s = data.split(" ");//分割字符串
        TreeNode head = new TreeNode(Integer.parseInt(s[0]));//头节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int i=1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!s[i].equals("!")) {//左节点不为空
                node.left = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.left);
            }
            i++;
            if(!s[i].equals("!")) {//右节点不为空
                node.right = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.right);
            }
            i++;
        }
        return head;
    }
```

**复杂度分析：**

时间复杂度：O(N)

空间复杂度：O(N) 

## 剑指 Offer 38. 字符串的排列

### 题目描述

[原题链接](https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ArrangementOfStrings.java)

输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

**示例:**

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

 

**限制：**

```
1 <= s 的长度 <= 8
```

### 题解

#### 回溯

递归回溯找出所有的可能，需要数组标记数组来记录哪些位置访问过。为了去重，可以将字符串转成数组对其进行排序，在递归前先判断这个元素是否之前已经出现过。避免出现`aab`，以第一个`a`为开头后又以第二个`a`为开头

```java
    boolean[] visited;//标记数组
    StringBuilder record = new StringBuilder();
    List<String> list = new ArrayList<>();
    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] array = s.toCharArray();
        Arrays.sort(array);//排序
        record(array);
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
    public void record(char[] s){
        if(record.length()==s.length){
            list.add(new String(record));
        }
        for (int i = 0; i < s.length; i++) {
            if(visited[i]||(i>0&&!visited[i-1]&&s[i-1]==s[i])){//去重
                continue;
            }
            record.append(s[i]);
            visited[i] = true;
            record(s);
            //恢复初始状态
            record.deleteCharAt(record.length()-1);
            visited[i] = false;
        }
    }
```

**复杂度分析：**

时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。这些字符的全部排列有 O(n!) 个，每个排列平均需要 O(n) 的时间来生成

空间复杂度：O(n)。我们需要 O(n) 的栈空间进行回溯，标记数组根据题目要求长度不会超过8，返回值不计入空间复杂度

#### 下一个排列

[下一个排列](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/medium/NextPermutation.java)

先将字符串转成字符数组，然后进行从小到大的排序，排序后的结果便是一个最小排列，以这个最小排列开始去寻找下一个排列，直到找完

```java
    List<String> list2 = new ArrayList<>();
    public String[] permutation2(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);//排序
        do {
            list2.add(new String(array));
        } while (next(array));
        String[] result = new String[list2.size()];
        return list2.toArray(result);
    }

    public boolean next(char[] array) {//下一个排列，返回true表示存在
        int left = array.length - 2;
        while (left >= 0 && array[left] >= array[left + 1]) {//寻找较小的值
            left--;
        }
        if (left < 0) {//越界判断
            return false;
        }
        int right = array.length - 1;
        while (right >= 0 && array[right] <= array[left]) {//在较小值的基础上寻找一个较大值
            right--;
        }
        if (right >= array.length) {//越界判断
            return false;
        }

        //交换较小值与较大值
        char target = array[left];
        array[left] = array[right];
        array[right] = target;
        left = left + 1;
        right = array.length - 1;
        while (left < right) {//对原较小值位置后面的元素进行排序
            //排序只需要左右交换就可以了
            target = array[left];
            array[left] = array[right];
            array[right] = target;
            left++;
            right--;
        }
        return true;
    }
```

**复杂度分析：**

时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。我们需要 O(*nlogn*) 的时间得到第一个排列，next 函数的时间复杂度为 O(n)，我们至多执行该函数 O(n!)次，因此总时间复杂度为 O(n×n!)

空间复杂度：O(1)，返回值不计入空间复杂度

## 剑指 Offer 39. 数组中出现次数超过一半的数字

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ANumberThatAppearsMoreThanHalfTheTimeInTheArray.java)

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

**示例 1:**

```
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

 

**限制：**

```
1 <= 数组长度 <= 50000
```

 

### 题解

#### Boyer-Moore 投票算法

具体思路可以参考：[多数元素](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/simple/MajorityElement.java)

将数组元素看作获得选票的人，只要有超过一半选票的人就能获胜。设置一变量记录当前得票最高的人，一变量记录有效票数（相同票加1，不同票减1，票数为0时换人）则遍历结束后超过一半选票的人一定会获胜

```java
    public int majorityElement(int[] nums) {
        int result = 0;//获得选票的人
        int count = 0;//当前票数
        for (int num : nums) {
            if (count == 0) {//为0换人
                result = num;
            }
            count = result == num ? count + 1 : count - 1;//相同票加1，不同票减1
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 40. 最小的k个数

### 题目描述

[原题链接](https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheSmallestKNumber.java)

输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 

**示例 1：**

```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```

**示例 2：**

```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

 

**限制：**

- `0 <= k <= arr.length <= 10000`
- `0 <= arr[i] <= 10000`

### 题解

#### 排序

将数组进行按升序排序后取前k个数

```java
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }
```

**复杂度分析：**

时间复杂度：O(nlog⁡n)，其中 n 是数组 arr 的长度。算法的时间复杂度即排序的时间复杂度

空间复杂度：O(log⁡n)，排序所需额外的空间复杂度为 O(log⁡n)

#### 堆

用一个大根堆实时维护数组的前 k 小值。首先将前 k 个数插入大根堆中，随后从第 k+1个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，就把堆顶的数弹出，再插入当前遍历到的数。最后将大根堆里的数存入数组返回即可。

```java
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0) { // 排除 0 的情况
            return result;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((num1, num2) -> num2 - num1);//降序排列
        for (int i = 0; i < k; i++) {//插入前k个数
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {//当前遍历到的数比大根堆的堆顶的数要小
                queue.poll();//弹出
                queue.offer(arr[i]);//插入
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(nlog⁡k)，其中 n 是数组 arr 的长度。由于大根堆实时维护前 k 小值，所以插入删除都是 O(log⁡k) 的时间复杂度，最坏情况下数组里 n 个数都会插入，所以一共需要 O(nlog⁡k) 的时间复杂度

空间复杂度：O(k)，因为大根堆里最多 k 个数

## 剑指 Offer 41. 数据流中的中位数

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMedianInTheDataStream.java)

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

- void addNum(int num) - 从数据流中添加一个整数到数据结构中。
- double findMedian() - 返回目前所有元素的中位数。

**示例 1：**

```
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```

**示例 2：**

```
输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]
```

 

**限制：**

- 最多会对 `addNum、findMedian` 进行 `50000` 次调用。

### 题解

#### 优先队列

使用两个优先队列minQueue，maxQueue。minQueue用来保存小于或者等于中位数的数值，降序排列。maxQueue来保存大于中位数的数值，升序排列。添加元素始终要保证中位数可以由队头获取，即需要维持两个队列数量上的平衡，minQueue的元素数量可以比maxQueue多一个（奇数情况）或者两者相同（偶数情况）

```java
class MedianFinder2 {
    PriorityQueue<Integer> minQueue;//大顶堆
    PriorityQueue<Integer> maxQueue;//小顶堆

    public MedianFinder2() {
        minQueue =  new PriorityQueue<Integer>((a,b)->(b-a));//降序
        maxQueue =  new PriorityQueue<Integer>((a,b)->(a-b));//升序
    }
    public void addNum(int num) {
        if(minQueue.isEmpty()||num<=minQueue.peek()){//需要添加的数值小于或等于minQueue队头
            minQueue.offer(num);//添加到minQueue
            if(minQueue.size()>maxQueue.size()+1){//调整，维持平衡
                maxQueue.offer(minQueue.poll());
            }
        } else {//需要添加的数值大于minQueue队头
            maxQueue.offer(num);//添加到maxQueue
            if(minQueue.size()<maxQueue.size()){//调整，维持平衡
                minQueue.offer(maxQueue.poll());
            }
        }

    }
    public double findMedian() {//返回中位数
        return minQueue.size()<=maxQueue.size()?(minQueue.peek()+maxQueue.peek())/2.0:minQueue.peek();
    }
}
```

**复杂度分析：**

时间复杂度：

addNum： O(logn)，其中 n 为累计添加的数的数量。findMedian：O(1)。

空间复杂度：O(n)，主要为优先队列的开销

## 剑指 Offer 42. 连续子数组的最大和

### 题目描述

[原题链接](https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/MaximumSumOfContiguousSubarray.java)

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

 

**示例1:**

```
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

 

**提示：**

- `1 <= arr.length <= 10^5`
- `-100 <= arr[i] <= 100`

### 题解

#### 动态规划

设置数组 dp[i] 表示以 nums[i] 结尾的子数组和的最大值，转换方程为`dp[i] = Math.max(dp[i-1] + nums[i],nums[i])`，在数组遍历过程中，我们发现要求dp[i]，只需要知道dp[i-1]即可，所以可以只用一个变量来记录dp[i-1]。

```java
    public int maxSubArray(int[] nums) {
        int record = 0;//记录dp[i-1]
        int max = nums[0];//记录最大值
        for (int num : nums) {
            record = Math.max(record + num, num);//转换方程
            max = Math.max(max, record);//更新最大值
        }
        return max;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为 nums 数组的长度。只需要遍历一遍数组即可求得答案

空间复杂度：O(1)，只需要常数空间存放若干变量

## 剑指 Offer 43. 1～n 整数中 1 出现的次数

### 题目描述

[原题链接](https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/NumberOfTimesThatOneOccursInTheIntegerFromOneToN.java)

输入一个整数 `n` ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

 

**示例 1：**

```
输入：n = 12
输出：5
```

**示例 2：**

```
输入：n = 13
输出：6
```

 

**限制：**

- `1 <= n < 2^31`

### 题解

#### 动态规划

设置数组dp[]，dp[i]表示 i 位数字一共能包含多少1，dp[1] 表示 [1,9]，1出现的次数为1；dp[2] 表示[10,99]，1出现的次数为20……。我们可以发现一些规律：`数字1—99中 1 出现的次数 = （1—9 中 1 出现的次数） + （X0—X9 中 1 出现的次数）+ 10，其中X属于[1,9]，10 表示首位为1的数字`，。递推关系为`dp[i] = dp[i-1]*10+Math.pow(10,i-1)`，因为题目限制了输入数字为 int 类型，我们可以提前将dp数组求出。

求一个具体数字我们可以将这个数字按位分解进行统计，思路与上面类似

例如 1~451中1出现的次数 = 1~400中中1出现的次数 + 1 + 1~50中1出现的次数 + 1~1中1出现的次数

```java
  long[] dp;//dp数组，分别记录1，10，10……中1出现的次数
    public int countDigitOne(int n) {
        dp = new long[11];
        dp[0]=0;
        dp[1]=1;
        for (int i = 2; i < 10; i++) {
            dp[i] = (long) (dp[i-1]*10+Math.pow(10,i-1));//先求出dp数组
        }
        int digits = 0;
        int result = 0;
        int pre = 0;//后面的数字，例如451，4后面的数字为51，用于处理数字1
        while (n>0){//从个位开始
            digits++;//记录数字所在位数
            result+=count(n%10,digits,pre);//求解该位数字对应数值有多少个1
            pre += n%10*Math.pow(10,digits-1);//更新后面数字
            n/=10;
        }
        return result;
    }

    public int count(int num,int n,int pre){//计算对应位整数1的个数
        int result = (int) (num*dp[n-1]);
        if(num>1){//大于1，需要对1为首位的数字进行额外处理
            result+=Math.pow(10,n-1);
        } else if(num==1){//等于1需要加上后面数字与自己
            result+=pre+1;
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(log⁡n)。n包含的数位个数与 n 呈对数关系，因为int类型数字位数不可能超过10，时间复杂度接近O(1)

空间复杂度：O(1)

## 剑指 Offer 44. 数字序列中某一位的数字

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ADigitInASequenceOfDigits.java)

数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。

 

**示例 1：**

```
输入：n = 3
输出：3
```

**示例 2：**

```
输入：n = 11
输出：0
```

 

**限制：**

- `0 <= n < 2^31`

### 题解

#### 找规律

| 数字范围  | 位数 | 占多少位 |
| --------- | ---- | -------- |
| 0-9       | 1    | 9        |
| 10-99     | 2    | 180      |
| 100-999   | 3    | 2700     |
| 1000-9999 | 4    | 36000    |

`占多少位 = 位数*9*该位对应的最小值`，如`36000 = 4*9*1000`

根据数字n，可以先求出其所处的位数

例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;

数据为 = 1000 + (12 - 1)/ 4  = 1000 + 2 = 1002

定位1002中的位置 = (n - 1) %  4 = 3    s.charAt(3) = 2;

*12-1是因为1000是第一个数*

```java
    public int findNthDigit(int n) {
        int digits = 1;//位数
        long num = 1;//位数对应的最小值
        long count = 9;//占多少位
        while (n>count){
            n-=count;
            digits++;
            num*=10;
            count = digits*9*num;
        }
        long result = num+(n-1)/digits;//所处的数字
        return Long.toString(result).charAt((n - 1) % digits) - '0';
    }
```

**复杂度分析：**

时间复杂度：O(d)，d 为 n 的位数

空间复杂度：O(1)

## 剑指 Offer 46. 把数字翻译成字符串

### 题目描述

[原题链接](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TranslateNumbersIntoStrings.java)

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 

**示例 1:**

```
输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
```

 

**提示：**

- `0 <= num < 231`

### 题解

#### 动态规划

```
1	    1
12  	2
122	    3
1222    5
1225    5
12258   5
12222   8
122224  12
```

通过这些示例可以发现 122的翻译翻译种数 = 12的翻译种树+1的翻译种数，12258的翻译种数 = 1225的翻译种数。

122相当于在 12 的基础上添加1个2，加上2之后22也可以作为一种组合被翻译，所以122的翻译翻译种数 = 12的翻译种树+1的翻译种数

12258相当于在1225的基础上加一个8，58不能作为一个组合，所以12258的翻译种数 = 1225的翻译种数

设数组nums[i]表示从第0位到第i位组成的数字能被翻译的数量，若nums[i]能与nums[i-1]对于的数字进行组合，则`nums[i]=nums[i-1]+nums[i-2]`，负责`nums[i]=nums[i-1]`

```java
    public int translateNum(int num) {
        int record1 = 1;//nums[i-2]
        int record2 = 1;//nums[i-1]
        if(num<10){
            return 1;
        }
        char[] array = (num + "").toCharArray();
        for (int i = 1; i < array.length; i++) {
            if(array[i-1]!='0'&&(array[i-1]-'0')*10+(array[i]-'0')<26){
                int flag = record2;
                record2 = record1+record2;
                record1 = flag;
            } else {
                record1 = record2;
            }

        }
        return record2;
    }
```

**复杂度分析：**

时间复杂度：循环的次数是 n 的位数，故渐进时间复杂度为 O(log⁡n)

空间复杂度：动态规划部分的空间代价是 O(1) ，但是这里用了一个临时变量把数字转化成了字符串，故渐进空间复杂度也是 O(log⁡n)

## 剑指 Offer 47. 礼物的最大价值

### 题目描述

[原题链接](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMaximumValueOfTheGift.java)

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

**示例 1:**

```
输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```

 

提示：

- `0 < grid.length <= 200`
- `0 < grid[0].length <= 200`

### 题解

#### 动态规划

设置dp数组，`dp[i][j]`表示走到 i，j 位置时的最大值。`dp[i][j]`很容易由`dp[i-1][j]`和`dp[i][j-1]`推出（如果存在的话）

转换方程为（i，j 均大于等于1且不越界）

`dp[0][0] = grid[0][0];`

`dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];`

`dp[0][i] = dp[0][i-1]+grid[0][i];`

`dp[i][0] = dp[i-1][0]+grid[i][0];`

```java
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i <grid[0].length ; i++) {//第一行
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i <grid.length ; i++) {//第一列
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {//从（1，1）开始
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[grid.length-1][dp[0].length-1];
    }
```

**复杂度分析：**

时间复杂度： O(mn)， m，n 分别为矩阵行高、列宽。需要遍历整个 grid 矩阵，使用 O(mn)时间

空间复杂度：O(n)，如果原地修改使用常数大小的额外空间

## 剑指 Offer 48. 最长不含重复字符的子字符串

### 题目描述

[原题链接](https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheLongestSubstringWithoutRepeatingCharacters.java)

### 题解

#### 滑动窗口

使用哈希表来存储节点。使用两个指针left和right，一开始均指向第一个字符，先固定左指针，向右移动右指针，若右指针指向的字符未出现在哈希表中则添加字符后继续移动；若出现则移动左指针（移动前先删除在哈希表中对应的值）

```java
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (left<=right&&right<s.length()){
            while (right<s.length()&&!set.contains(s.charAt(right))){//移动右指针
                set.add(s.charAt(right));
                right++;
            }
            max = Math.max(max,set.size());//记录最大值
            //移动左指针
            set.remove(s.charAt(left));
            left++;
        }
        return max;
    }
```

也可以用队列来代替哈希表

```java
    public int lengthOfLongestSubstring2(String s) {
        Queue<Character> queue = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(queue.contains(c)){
                max = Math.max(queue.size(),max);
                while (queue.peek()!=c){
                    queue.poll();
                }
                queue.remove(c);
            }
            queue.add(c);
        }
        max = Math.max(queue.size(),max);
        return max;
    }
```

**复杂度分析：**

时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次

空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在[0,128) 内的字符，即 ∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣个，因此空间复杂度为 O(∣Σ∣)

## 剑指 Offer 49. 丑数

### 题目描述

[原题链接](https://leetcode.cn/problems/chou-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/UglyNumber.java)

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

 

**示例:**

```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```

**说明:** 

1. `1` 是丑数。
2. `n` **不超过**1690。

### 题解

#### 最小堆

可以用最小堆来维护丑数顺序，堆顶是最小丑数。每次循环取出堆顶元素，记为x，则2x，3x，5x同样也为丑数，加入堆中，为了去重可用使用哈希表来存储之前丑数，将元素放入堆中之前先判断是否已存在。

在排除重复元素的情况下，第 n 次从最小堆中取出的元素即为第 n 个丑数。

```java
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();//哈希表
        int[] adds = new int[]{2,3,5};
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();//最小堆，默认堆顶为最小元素
        set.add(1L);
        priorityQueue.offer(1L);
        int result = 0;
        for (int i = 0; i < n; i++) {
            long poll = priorityQueue.poll();
            result = (int)poll;
            for (int add : adds) {//将 2x，3x，5x入堆
                Long num = add*poll;
                if(!set.contains(num)){
                    set.add(num);
                    priorityQueue.add(num);
                }
            }
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(nlog⁡n)。得到第 n 个丑数需要进行 n 次循环，每次循环都要从最小堆中取出 1 个元素以及向最小堆中加入最多 3 个元素，因此每次循环的时间复杂度是 O(log⁡(3n)+3log⁡(3n))，总时间复杂度是O(nlogn)

空间复杂度：O(n)。空间复杂度主要取决于最小堆和哈希集合的大小，最小堆和哈希集合的大小都不会超过 3n

#### 动态规划

第一个丑数是1，将1与2，3，5分别做乘法获得下一批丑数，将这些丑数做同样的操作又可以获得一批丑数。不考虑数量与顺序的情况下，每一个丑数都需要与2，3，5做乘法。

但我们需要考虑顺序，可以设置三个指针p1，p2，p3分别表示与2，3，5做乘法。设 dp[i] 表示第 i-1 个丑数，dp[0]=1

则有`dp[i]=Math.min(Math.min(dp[p1]*2,dp[p2]*3),dp[p3]*5);`，同时还需要确定dp[i]是哪个（或哪些）指针计算的结果，将相应的指针进行后移

```
     2 		3  		4  		5		6		8		9		10
p1   1*2 	2*2		2*2		3*2		3*2		4*2		5*2		5*2
p2   1*3	1*3		2*3		2*3		2*3		3*3		3*3		4*3
p3   1*5 	1*5		1*5		1*5		2*5		2*5		2*5		2*5
```

```java
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p1 = 0,p2=0,p3=0;
        for (int i = 1; i <n ; i++) {
            int num1 = dp[p1]*2,num2 = dp[p2]*3,num3 = dp[p3]*5;
            int num = Math.min(Math.min(num1,num2),num3);
            dp[i]=num;
            if(num1==dp[i]){
                p1++;
            }
            if(num2==dp[i]){
                p2++;
            }
            if(num3==dp[i]){
                p3++;
            }
        }
        return dp[n-1];
    }
```

**复杂度分析：**

时间复杂度：O(n)。需要计算数组 dp 中的 n 个元素，每个元素的计算都可以在 O(1) 的时间内完成

空间复杂度：O(n)。空间复杂度主要取决于数组 dp 的大小

## 剑指 Offer 50. 第一个只出现一次的字符

### 题目描述

[原题链接](https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheFirstCharacterThatAppearsOnlyOnce.java)

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

**示例 1:**

```
输入：s = "abaccdeff"
输出：'b'
```

**示例 2:**

```
输入：s = "" 
输出：' '
```

 

**限制：**

```
0 <= s 的长度 <= 50000
```

### 题解

#### 哈希表

可以使用数组来存储字符串中字符的出现次数，需要遍历字符串两次，第一次遍历记录出现次数，第二次遍历寻找第一个只出现一次的字符（因为字符串由小写字母组成，数组下标很容易与字符对应起来）

```java
    public char firstUniqChar(String s) {
        int[] nums = new int[26];//26个小写字母
        for (int i = 0; i < s.length(); i++) {//记录次数
            nums[s.charAt(i)-'a']++;
        }
        for (int i = 0; i <s.length() ; i++) {//寻找第一个只出现一次的字符
            if(nums[s.charAt(i)-'a']==1){
                return s.charAt(i);
            }
        }
        return ' ';
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是字符串 s 的长度。我们需要进行两次遍历。

空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们只需要 O(∣Σ∣) 的空间

当然也可以用哈希表来存储字符与其位置，若字符已经在哈希表中出现过，则位置置为-1。由于字符串是小写字母组成在哈希表中的存储顺序是不会改变的（若一定要保持插入顺序，可以使用`java.util.LinkedHashMap`），第二次遍历可以直接遍历哈希表

```java
    public char firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {//遍历字符串
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = s.length();
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {//遍历哈希表
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        return first == s.length() ? ' ' : s.charAt(first);
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是字符串 s 的长度。第一次遍历字符串的时间复杂度为 O(n)，第二次遍历哈希映射的时间复杂度为 O(∣Σ∣)，由于 s 包含的字符种类数一定小于 s 的长度，因此 O(∣Σ∣) 在渐进意义下小于 O(n)，可以忽略

空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们需要 O(∣Σ∣) 的空间存储哈希映射

## 剑指 Offer 51. 数组中的逆序对

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ReversePairsInAnArray.java)

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

**示例 1:**

```
输入: [7,5,6,4]
输出: 5
```

 

**限制：**

```
0 <= 数组长度 <= 50000
```

### 题解

#### 归并排序

**参考：**[剑指 Offer 51. 数组中的逆序对（归并排序，清晰图解）](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solutions/622496/jian-zhi-offer-51-shu-zu-zhong-de-ni-xu-pvn2h/)

数组 `[7,3,2,6,0,1,5,4] `的归并排序过程如下

![](https://pic.leetcode-cn.com/1614274007-nBQbZZ-Picture1.png)

我们可以在合并过程中来统计逆序对，例如合并`[2,3,6,7]`与`[0,1,4,5]`，设置两个指针i，j分别指向两数组首部，2比0大，则i指针后面的元素都比0大（这两个数组都是已经排序的），也就是说指针 i 指向的2以及其后面的元素与0都能组成逆序对，其它元素的比较同理。可以由此来统计归并过程中产生的逆序对

![](https://pic.leetcode-cn.com/1614274007-rtFHbG-Picture2.png)

```java
    int[] temp;
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
```

**复杂度分析：**

时间复杂度：O(nlog⁡n)，其中 n 为数组长度；归并排序使用 O(nlog⁡n) 时间

空间复杂度：O(n)，辅助数组 tmp 占用 O(n) 大小的额外空间

## 剑指 Offer 52. 两个链表的第一个公共节点

### 题目描述

[原题链接](https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheFirstPublicNodeOfTwoLinkedLists.java)

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

 

**示例 1：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

 

**示例 2：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

 

**示例 3：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```

 

**注意：**

- 如果两个链表没有交点，返回 `null`.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(*n*) 时间复杂度，且仅用 O(*1*) 内存。

### 题解

#### 双指针

对于存在公共节点的两个链表，我们第一想法就是如果在这个公共节点之前这两个链表存在的节点个数相同，就很好发现这个公共节点（直接循环比较即可）。所以我们可以提前遍历这两个链表来获取链表的长度，根据这个长度来调整链表

```java
   ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode head = headA;
        //获取链表长度
        while (head != null) {
            lengthA++;
            head = head.next;
        }
        head = headB;
        while (head != null) {
            lengthB++;
            head = head.next;
        }
        //调整链表
        while (lengthA != lengthB) {
            if (lengthA < lengthB) {
                lengthA++;
                headB = headB.next;
            } else {
                lengthB++;
                headA = headA.next;
            }
        }
	    //遍历寻找公共节点
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

还有一种写法不需要获取链表长度，headA和headB同时遍历，如果headA为空，则指向headB的头节点；如果headB为空，则指向headA的头节点，同时为空返回null。思路与上面类似，都是保证在遍历过程中两个链表的指针能同时访问到公共节点

```java
    ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p!=q) {
            p = p==null?headB:p.next;
            q = q==null?headA:q.next;
        }
        return p;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 53 - I. 在排序数组中查找数字 I

### 题目描述

[原题链接](https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/FindsANumberInASortedArray.java)

统计一个数字在排序数组中出现的次数。

 

**示例 1:**

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```

**示例 2:**

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```

 

**提示：**

- `0 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`
- `nums` 是一个非递减数组
- `-109 <= target <= 109`

### 题解

#### 二分查找

因为是有序数组，所以可以使用二分查找，分别查找第一个大于等于target的nums[left]和第一个小于等于target的nums[right]，返回结果便是right-left+1

```java
    public int search(int[] nums, int target) {
        int left = searchM(nums, target);
        int right = searchN(nums, target);
        return right-left+1;
    }

    public int searchM(int[] nums, int target){//寻找第一个大于等于target的nums[i]
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]<target){
                left = mid+1;
            } else {
                right = mid - 1 ;
            }
        }
        return left;
    }

    public int searchN(int[] nums, int target){//寻找第一个小于等于target的nums[i]
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]>target){
                right = mid - 1;
            } else {
                left = mid+1 ;
            }
        }
        return right;
    }
```

**复杂度分析：**

时间复杂度： O(log⁡n)，其中 n 为数组的长度。二分查找的时间复杂度为 O(log⁡n)，一共会执行两次，因此总时间复杂度为 O(log⁡n)

空间复杂度：O(1)。只需要常数空间存放若干变量

##  剑指 Offer 53 - II. 0～n-1中缺失的数字

### 题目描述

[原题链接](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheNumberIsMissingFrom0ToNMinus1.java)

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

 

**示例 1:**

```
输入: [0,1,3]
输出: 2
```

**示例 2:**

```
输入: [0,1,2,3,4,5,6,7,9]
输出: 8
```

 

**限制：**

```
1 <= 数组长度 <= 10000
```

### 题解

#### 直接遍历

直接遍历寻找第一个`nums[i]!=i`的 i 值返回即可，对于特殊情况`[0]`，需要返回1

```java
    public int missingNumber(int[] nums) {
        int i = 0;
        for (; i < nums.length; i++) {
            if(i!=nums[i]){
                return i;
            }
        }
        return i;
    }
```

**复杂度分析：**

时间复杂度： O(n)

空间复杂度：O(1)

#### 二分查找

既然是有序数组，按道理就可以使用二分查找。寻找第一个`nums[i]!=i`的 i 值

```java
public int missingNumber2(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==mid){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }
```

**复杂度分析：**

时间复杂度： O(log⁡n)

空间复杂度：O(1)

## 剑指 Offer 54. 二叉搜索树的第k大节点

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheKTHLargestNodeOfTheBinarySearchTree.java)

给定一棵二叉搜索树，请找出其中第 `k` 大的节点的值。

 

**示例 1:**

```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
```

**示例 2:**

```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
```

 

**限制：**

- 1 ≤ k ≤ 二叉搜索树元素个数

### 题解

二叉搜索树的 **中序遍历倒序** 为 **递减序列** 。因此，求 “二叉搜索树第 k 大的节点” 可转化为求 “此树的中序遍历倒序的第 k 个节点”

```java
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null)
            return;
        dfs(root.right);
        if(k == 0)
            return;
        if(--k == 0)
            res = root.val;
        dfs(root.left);
    }
```

**复杂度分析：**

时间复杂度：O(N)，当树退化为链表时（全部为右子节点），无论 k 的值大小，递归深度都为 N ，占用 O(N) 时间。

空间复杂度：O(N)，当树退化为链表时（全部为右子节点），系统使用 O(N) 大小的栈空间。

## 剑指 Offer 55 - I. 二叉树的深度

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheDepthOfTheBinaryTree.java)

输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：

给定二叉树 `[3,9,20,null,null,15,7]`，

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。

 

**提示：**

1. `节点总数 <= 10000`

### 题解

#### 深度优先搜索

对于一个节点，如果我们知道其左子树的深度left以及右子树的深度right，则以这个节点为根节点树的深度为`Math,max(left,right)+1`;

```java
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = maxDepth(root.left);//左子树的深度
        int right = maxDepth(root.right);//右子树的深度
        return Math.max(left,right)+1;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次

空间复杂度：O(height)，其中 height 表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度

#### 广度优先搜索

层次遍历，使用一变量来记录深度，每遍历一层变量值就加1

广度优先搜索的队列里存放的是「当前层的所有节点」。每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，我们需要将队列里的所有节点都拿出来进行拓展，这样能保证每次拓展完的时候队列里存放的是当前层的所有节点。

```java
    public int maxDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return 0;
        }
        int count = 0;
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){
                TreeNode poll = queue.poll();
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
                size--;
            }
            count++;
        }
        return count;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为二叉树的节点个数。每个节点只会被访问一次

空间复杂度：此方法空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到 O(n)

## 剑指 Offer 55 - II. 平衡二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/BalancedBinaryTree.java)

输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

**示例 1:**

给定二叉树 `[3,9,20,null,null,15,7]`

```
    3
   / \
  9  20
    /  \
   15   7
```

返回 `true` 。

**示例 2:**

给定二叉树 `[1,2,2,3,3,null,null,4,4]`

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

返回 `false` 。

 

**限制：**

- `0 <= 树的结点个数 <= 10000`

### 题解

#### 自底向上递归

思路其实与`二叉树的深度`相同，判断左子树与右子树深度差，超过 1 则不满足平衡二叉树的要求

```java
    public boolean isBalanced(TreeNode root) {
        return depth(root)> -1;
    }

    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if(left-right>1||right-left>1||left==-1||right==-1){
            return -1;
        }
        return Math.max(left,right)+1;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 nnn 是二叉树中的节点个数。使用自底向上的递归，每个节点的计算高度和判断是否平衡都只需要处理一次，最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)

空间复杂度：O(n)，其中 nnn 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n

## 剑指 Offer 56 - I. 数组中数字出现的次数

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheNumberOfTimesANumberAppearsInTheArray.java)

一个整型数组 `nums` 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 

**示例 1：**

```
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
```

**示例 2：**

```
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```

 

**限制：**

- `2 <= nums.length <= 10000`

 

### 题解

#### 分组异或

如果只有一个数字只出现一次，则可以设置一个变量result，初始值为0，遍历这个数组nums，`result^=num`，最终result的结果便是这个只出现一次的数字。任何数与0异或结果都为任何数，任何数与自身异或结果都为0，且异或具有交换性，即出现两次的数字会抵消为0，只剩下只出现一次的数字。

现在有两个这样只出现一次的数字，则按照上面的方法result为这两个数字异或的结果。

需要将这些元素进行分组，使这两个数字分离开再使用上述方法。可以根据这两个数字异或的结果进行分组（记结果为result），二进制异或的特点：相同为0，不同为1，我们只有找到 result 为1的那一位即可，在这一位上，需要求的这两个数字肯定不同，根据这个不同进行分组异或即可求出这两个数字

```java
    public int[] singleNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        int dividingLine = 1;
        while ((result & dividingLine) == 0) {
            dividingLine <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if((num&dividingLine)==0){
                a^=num;
            } else {
                b^=num;
            }
        }
        return new int[]{a,b};
    }
```

**复杂度分析：**

时间复杂度：O(n)，我们只需要遍历数组两次

空间复杂度：O(1)，只需要常数的空间存放若干变量

## 剑指 Offer 56 - II. 数组中数字出现的次数 II

### 题目描述

[题目链接](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/NumberOfOccurrencesOfANumberInTheArrayII.java)

在一个数组 `nums` 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 

**示例 1：**

```
输入：nums = [3,4,3,3]
输出：4
```

**示例 2：**

```
输入：nums = [9,1,7,9,7,9,7]
输出：1
```

 

**限制：**

- `1 <= nums.length <= 10000`
- `1 <= nums[i] < 2^31`

### 题解

#### 有限状态自动机

**参考：** [面试题56 - II. 数组中数字出现的次数 II（位运算 + 有限状态自动机，清晰图解）](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solutions/215895/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/)

考虑数字的二进制形式，对于出现三次的数字，各 二进制位 出现的次数都是 3 的倍数。 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。

![](https://pic.leetcode-cn.com/28f2379be5beccb877c8f1586d8673a256594e0fc45422b03773b8d4c8418825-Picture1.png)

我们只考虑其中一位，在相加过程中对3求余中可能出现的结果为 0，1，2。2无法用一位二进制来表示，所以我们用两位来表示。0，1，2对应的表示方式为 00，01，10。在相加过程中这一位如果需要加0，则状态不变，加1则00—>01，01—>10，10—>00

![](https://pic.leetcode-cn.com/0a7ea5bca055b095673620d8bb4c98ef6c610a22f999294ed11ae35d43621e93-Picture3.png)

对于one的改变可以由以下代码控制

```
if two == 0
  if n == 0
    one = one
  if n == 1
    one = ~one
if two == 1
    one = 0
```

其中

```
  if n == 0
    one = one
  if n == 1
```

可以采用位运算简写为`one^n`，任何数与0异或值不变，与1异或值取反

控制代码可改为

```
if two == 0
	one=one^n
if two == 1
    one = 0
```

可以采用位运算简写为`one = (one^num)&(~two);`，之所以要采用位运算来替代代码控制是因为位运算可以扩展到多位

对于two的求法，可以发现与one的求法相同（此时one的值已经更新）

![](https://pic.leetcode-cn.com/6ba76dba1ac98ee2bb982e011fdffd1df9a6963f157b2780461dbce453f0ded3-Picture5.png)

即`two = (two^num)&(~one);`

对所有位相加对3求余的最终结果可以发现，不会出现10，只会出现00，01。就结果而言two的值不重要，只需要用one来表示（0或1取决于目标数字对应位上的值）。所以只出现一次的数字的二进制由one构成。

```java
    public int singleNumber(int[] nums) {
        int two=0,one=0;//多个位的组合
        for (int num : nums) {
            one = (one^num)&(~two);
            two = (two^num)&(~one);
        }
        return one;
    }
```

**复杂度分析：**

时间复杂度：O(N)，其中 N 为数组 nums 的长度，遍历数组占用 O(N)，每轮中的常数个位运算操作占用O(1) 

空间复杂度：O(1)

## 剑指 Offer 57. 和为s的两个数字

### 题目描述

[原题链接](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheSumIsTwoNumbersOfS.java)

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

 

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
```

**示例 2：**

```
输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
```

 

**限制：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^6`

### 题解

#### 双指针

数组是排序的，可以使用双指针，left 指向数组首部，right 指向数组尾部，若两者对应数值相加为目标值则直接返回，大于目标值，右指针左移，小于则左指针右移

```java
    public int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left<right){
            int result = nums[left]+nums[right];
            if(result==target){
                return new int[]{nums[left],nums[right]};
            }
            if(result>target){
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
```

**复杂度分析：**

时间复杂度：O(N)，N 为数组 nums 的长度；最坏情况下双指针共同线性遍历整个数组

空间复杂度：O(1)

## 剑指 Offer 57 - II. 和为s的连续正数序列

### 题目描述

[原题链接](https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/AndIsAContinuousSequenceOfPositiveNumbersForS.java)

输入一个正整数 `target` ，输出所有和为 `target` 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 

**示例 1：**

```
输入：target = 9
输出：[[2,3,4],[4,5]]
```

**示例 2：**

```
输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

 

**限制：**

- `1 <= target <= 10^5`

### 题解

#### 双指针

滑动窗口的思想，设置两指针left，right初始值分别为1，2。计算left到right的和（求和可以用等差数列求和公式或者设置一变量来记录和），如果大于目标值移动左指针，小于目标值移动右指针，等于目标值时，可以根据循环条件选择移动1个或2个指针

```java
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int left = 1,right = 2,num = 3;//num记录和
        while (left<right){
            if(num==target){//保存数据，从left到right
                int[] nums = new int[right-left+1];
                for (int i = left; i <=right ; i++) {
                    nums[i-left]=i;
                }
                list.add(nums);
            }
            if(num<=target){//小于或者等于目标值都移动右指针
                right++;
                num+=right;//更新和
            } else {//大于目标值移动左指针
                num-=left;//更新和，需要提前更新
                left++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
```

**复杂度分析：**

时间复杂度：O(target) ，由于两个指针移动均单调不减，且最多移动 target/2 次

空间复杂度：O(1) ，除了答案数组只需要常数的空间存放若干变量
