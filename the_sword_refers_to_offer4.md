## 剑指 Offer 58 - I. 翻转单词顺序

### 题目描述

[原题链接](https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/FlipWordOrder.java)

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

 

**示例 1：**

```
输入: "the sky is blue"
输出: "blue is sky the"
```

**示例 2：**

```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```

**示例 3：**

```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```

 

**说明：**

- 无空格字符构成一个单词。
- 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
- 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

### 题解

#### 倒序分割

倒序遍历分割字符串，可以使用`split`函数进行分割（对于连续的空格会产生空串），也可以使用`StringBuilder`来拼接字符形成字符串

```java
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();//保存结果
        StringBuilder record = new StringBuilder();//用于拼接字符串
        int left = 0, right = s.length() - 1;
        //去掉字符串前面的空格
        while (left < s.length() && s.charAt(left) == ' ') {
            left++;
        }
        //去掉字符串后面的空格
        while (right >= 0 && s.charAt(right) == ' ') {
            right--;
        }
        while (left <= right) {
            char c = s.charAt(right);
            if (c == ' ') {//以空格为分割线
                result.append(record).append(' ');
                record.delete(0, record.length());
                while (right >= 0 && s.charAt(right) == ' ') {//去掉剩余空格
                    right--;
                }
            } else {
                record.insert(0, c);
                right--;
            }
        }
        result.append(record);
        return result.toString();
    }
```

```java
    public String reverseWords2(String s) {
        String[] strs = s.split(" ");//分割字符串
        StringBuilder result = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) continue;//处理连续空格产生的空串
            result.append(strs[i]).append(" ");
        }
        if (result.length() - 1 >= 0) result.delete(result.length() - 1, result.length());//去掉末尾空格
        return result.toString();
    }
```

**复杂度分析：**

时间复杂度：O(n)，总体为线性时间复杂度

空间复杂度：O(n)

## 剑指 Offer 58 - II. 左旋转字符串

### 题目描述

[原题链接](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/LeftRotatedString.java)

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 

**示例 1：**

```
输入: s = "abcdefg", k = 2
输出: "cdefgab"
```

**示例 2：**

```
输入: s = "lrloseumgh", k = 6
输出: "umghlrlose"
```

 

**限制：**

- `1 <= k < s.length <= 10000`

### 题解

#### 字符分割

可以使用`substring`方法获取分割的字符串进行拼接，或者遍历拼接（使用`StringBuilder`保存结果，先遍历后一部分，再遍历前一部分）

```java
    public String reverseLeftWords(String s, int n) {
        return s.substring(n + 1, s.length()) + s.substring(0, n + 1);
    }
```

```java
    public String reverseLeftWords2(String s, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            result.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(n)

## 剑指 Offer 59 - I. 滑动窗口的最大值

### 题目描述

[原题链接](https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMaximumValueOfTheSlidingWindow.java)

给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。

**示例:**

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

 

**提示：**

你可以假设 *k* 总是有效的，在输入数组 **不为空** 的情况下，`1 ≤ k ≤ nums.length`。

### 题解

#### 大顶堆

使用大顶堆来存储窗口中的元素，需要存储元素值和下标值，保证堆顶元素值最大的同时下标最大（值最大保证是窗口中最大的元素，下标最大保证该元素尽可能停留在窗口中），添加元素后需要判断堆顶元素是否在窗口中，不在则弹出直到堆顶元素在窗口中

```java
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        result[0] = priorityQueue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            priorityQueue.add(new int[]{nums[i], i});
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            result[i - k + 1] = priorityQueue.peek()[0];
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(nlog⁡n)，其中 n 是数组 nums 的长度。在最坏情况下，数组 nums 中的元素单调递增，那么最终优先队列中包含了所有元素，没有元素被移除。由于将一个元素放入优先队列的时间复杂度为 O(log⁡n)，因此总时间复杂度为 O(nlog⁡n)

空间复杂度：O(n)，即为优先队列需要使用的空间。这里所有的空间复杂度分析都不考虑返回的答案需要的 O(n) 空间，只计算额外的空间使用

#### 双端队列

使用双端队列来存储窗口下标，添加元素前总是先判断队尾元素是否小于需要添加的元素，小于则弹出队尾元素直到条件不满足，才王队列中添加元素，始终确保新添加的并且值最大的元素驻留在队列中，之前那些比较小的元素就没有必要存在了。

从队首取窗口中的最大元素的下标，在此之前先判断对首元素是否在窗口中，不在则弹出直到元素位于窗口中

```java
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        result[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            result[i - k + 1] = nums[deque.peekFirst()];
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 nnn 是数组 nums 的长度。每一个下标恰好被放入队列一次，并且最多被弹出队列一次，因此时间复杂度为 O(n)

空间复杂度：O(k)，「不断从队首弹出元素」保证了队列中最多不会有超过 k+1 个元素，因此队列使用的空间为 O(k)

#### 分块处理

根据k值将数组进行分块，`[0,k)，[k,2k)...`，使用两个数组前缀数组和后缀数组来存储预先处理的结果。前缀数组`prefix[i]`表示上一块边界到 i 的最大元素，后缀数组`suffix[i]`表示 i 到下一块边界的最大元素。

在预处理完成之后，对于 nums[i]到 nums[i+k−1] 的所有元素，如果 i 不是 k 的倍数，那么窗口中的最大值为 suffix[i]与 prefix[i+k−1] 中的较大值；如果 i 是 k 的倍数，那么此时窗口恰好对应一整个分组，suffix[i] 和 prefix[i+k−1]都等于分组中的最大值，因此无论窗口属于哪一种情况，`Math.max(prefix[i + k - 1], suffix[i])`即为结果

```java
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i % k == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = Math.max(nums[i], prefix[i - 1]);
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1 || (i + 1) % k == 0) {
                suffix[i] = nums[i];
            } else {
                suffix[i] = Math.max(nums[i], suffix[i + 1]);
            }
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = Math.max(prefix[i + k - 1], suffix[i]);
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是数组 nums 的长度。我们需要 O(n) 的时间预处理出数组 prefix和suffix 以及计算答案

空间复杂度：O(n)

## 剑指 Offer 60. n个骰子的点数

### 题目描述

[原题链接](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheRollOfNDice.java)

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 

**示例 1:**

```
输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
```

**示例 2:**

```
输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
```

 

**限制：**

```
1 <= n <= 11
```

### 题解

#### 动态规划

对于1个骰子

```
1		2		3		4		5		6
1/6		1/6		1/6		1/6		1/6		1/6
```

若在此基础上再增加一个骰子，计算数字和的概率，可以通过求第一个骰子对和的的贡献度来求出数字和的概率（增加的那个骰子每一个数字出现的概率都为1/6）

第一个骰子的数字1，对和为2，3，4，5，6，7都有贡献

第一个骰子的数字2，对和为3，4，5，6，7，8都有贡献

......

第一个骰子的数字6，对和为7，8，9，10，11，12都有贡献

设P表示第一个骰子数字出现的概率

和为2的概率 =` P(1)*1/6`，和为3的概率 = `P(1)*1/6`+`P(2)*1/6`......**1/6是新加的骰子对应数字出现的概率**，如和为3是，第一个骰子数字为1，则新加的骰子出现的数字必须为2，出现概率为1/6

设数组 result 为n-1个骰子数字和的概率情况，要求n个骰子的概率情况，则只需遍历 result，统计每个数字的贡献度即可

```java
    public double[] dicesProbability(int n) {
        double[] result = new double[6];
        Arrays.fill(result,1.0/6);//1个骰子
        for (int i = 2; i <=n ; i++) {
            double[] temp = new double[5*i+1];//5*i+1为i个骰子数字和的所有情况，即6n-n+1
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k <6 ; k++) {//新加的1个骰子，0-5对应1-6
                    temp[j+k] += result[j]/6.0;//1/6.0是新加的那个骰子对应数字出现的概率
                }
            }
            result = temp;//更新结果
        }
        return result;
    }
```



## 剑指 Offer 62. 圆圈中最后剩下的数字

### 题目描述

[原题链接](https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheLastNumberLeftInTheCircle.java)

0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

 

**示例 1：**

```
输入: n = 5, m = 3
输出: 3
```

**示例 2：**

```
输入: n = 10, m = 17
输出: 2
```

 

**限制：**

- `1 <= n <= 10^5`
- `1 <= m <= 10^6`

### 题解

#### 自底向上分析

```
设 n = 5, m = 3

n = 5， 0, 1, 2, 3, 4 => 0, 1, 2, 3, 4
n = 4， 3, 4, 0, 1     => 3, 4, 0, 1
n = 3， 1, 3, 4         => 1, 3, 4
n = 2， 1, 3             => 1, 3
n = 1， 3                 => END
```

n为数字个数，由于每趟从被删除元素之后开始数，把被删除的元素都移到数组中的第一个位序。那么，可以观察到被删除的总是下标为`(0 + m) mod n`的元素;删除元素后，其他的元素都向前移动了`m`位，最终坐落在坐标为`(index - m) mod (n -1)`的位置上(index为元素未移动前的坐标)

从下往上推导，可以肯定的是最后一趟(即n=1时), 只有一个数，而且其**坐标一定为0**，由于相比上一趟所有元素都移动了3位,那么应该往后挪3位才能推到原来的位置上。

所以有：

```
index2 = (index1 + 3) mod 2 = (0 + 3) mod 2 = 1
index3 = (index2 + 3) mod 3 = (1 + 3) mod 3 = 1
index4 = (index3 + 3) mod 4 = (1 + 3) mod 4 = 0
index5 = (index4 + 3) mod 5 = (0 + 3) mod 5 = 3
```

```java
    public int lastRemaining(int n, int m) {
        int result = 0;
        for (int i = 2; i != n + 1; ++i) {
            result = (m + result) % i;
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 63. 股票的最大利润

### 题目描述

[原题链接](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMaximumProfitOfTheStock.java)

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

**示例 2:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

 

**限制：**

```
0 <= 数组长度 <= 10^5
```

### 题解

#### 动态规划

`dp[i]`来表示第i天的最大利润，`minPrice`来记录前 i 天股票价格的最小值，则`dp[i]=Math.max(dp[i-1],prices[i]-minPrice)`，遍历数组进行计算即可获得最大利润

```java
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;//记录当前最小价格
        int profit = 0;//记录最大利润
        for (int price : prices) {
            minPrice = Math.min(minPrice,price);
            profit = Math.max(profit,price-minPrice);
        }
        return profit;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 64. 求1+2+…+n

### 题目描述

[原题链接](https://leetcode.cn/problems/qiu-12n-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/SumOfNumbers.java)

求 `1+2+...+n` ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

 

**示例 1：**

```
输入: n = 3
输出: 6
```

**示例 2：**

```
输入: n = 9
输出: 45
```

 

**限制：**

- `1 <= n <= 10000`

### 题解

#### 递归，短路

递归来代替循环，短路来代替 if 判断...如果使用求和公式即`n*(n+1)/2`，`/2`可以用`>>1`来代替，乘法用倍增思想来编写，因为不能用循环就只能面向题目硬写语句即把循环要干的事直接朴实无华的写出来，，，

```java
    public int sumNums(int n) {
        boolean b = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(n)，递归需要用到栈空间

## 剑指 Offer 65. 不用加减乘除做加法

### 题目描述

[原题链接](https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/AddWithoutAdditionSubtractionMultiplicationAndDivision.java)

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 

**示例:**

```
输入: a = 1, b = 1
输出: 2
```

 

**提示：**

- `a`, `b` 均可能是负数或 0
- 结果不会溢出 32 位整数

### 题解

#### 位运算

模拟二进制运算

```
	1	0	1
	0	0	1
本位 1   0   0
进位 0   1   0
结果 1   1   0
```

本位值可以看作是异或产生的结果（相同为0，否则为1），进位可以看作是且运算后左移一位（进位肯定是往前进一位），结果由进位+本位产生（重复上述操作直到进位为0）

```java
    public int add(int a, int b) {
        while (b!=0){
            int temp = b;
            b = (a&b)<<1;//进位
            a = a^temp;//本位
        }
        return a;
    }
```

**复杂度分析：**

时间复杂度：O(1)

空间复杂度：O(1)

## 剑指 Offer 66. 构建乘积数组

### 题目描述

[原题链接](https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/BuildProductArray.java)

给定一个数组 `A[0,1,…,n-1]`，请构建一个数组 `B[0,1,…,n-1]`，其中 `B[i]` 的值是数组 `A` 中除了下标 `i` 以外的元素的积, 即 `B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]`。不能使用除法。

 

**示例:**

```
输入: [1,2,3,4,5]
输出: [120,60,40,30,24]
```

 

**提示：**

- 所有元素乘积之和不会溢出 32 位整数
- `a.length <= 100000`

### 题解

#### 分批计算

不能用除法，可以设置两数组 left与right，`left[i] = left[i-1]*a[i-1]`，`left[i]`记录从0到i-1的乘积（不包括i），`right[i] = right[i+1]*a[i+1];`，`right[i]`记录从i到a.length的乘积，这样结果就为`result[i] = left[i]*right[i];`

```java
    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        if (a.length == 0) {
            return result;
        }
        int[] left = new int[a.length];
        left[0] = 1;
        int[] right = new int[a.length];
        right[a.length - 1] = 1;
        //记录从左到右的乘积
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        //记录从右到左的乘积
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        for (int i = 0; i < a.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }
```

**时间复杂度：**

时间复杂度：O(n)

空间复杂度：O(n)

也可以不用额外数组提前存储乘积，先使用数组result记录从左到右的乘积，再从左往右遍历设置一变量left记录从左到右对应的乘积，随着遍历去更新，最终结果`result[i] = left * result[i];`

```java
    public int[] constructArr2(int[] a) {
        int[] result = new int[a.length];
        if (a.length == 0) {
            return result;
        }
        result[a.length - 1] = 1;
        //记录从右往左的乘积
        for (int i = a.length - 2; i >= 0; i--) {
            result[i] = result[i + 1] * a[i + 1];
        }
        int left = 1;//记录从左往右的乘积
        for (int i = 0; i < a.length; i++) {
            result[i] = left * result[i];//更新结果数组
            left *= a[i];//更新left
        }
        return result;
    }
```

**时间复杂度：**

时间复杂度：O(n)

空间复杂度：O(1)

## 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMostRecentCommonAncestorOfABinarySearchTree.java)

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

 

**示例 1:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```

**示例 2:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
```

 

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉搜索树中。

### 题解

#### 寻找分水岭

当root节点的值处于p，q之间（包括p，q），则p，q一定分居root两侧，root 即为p，q最近公共祖先；若p，q都小于root的值，则p，q都在root左侧，继续遍历root的左子树；若p，q都大于root的值，则p，q都在root右侧，继续遍历root的右子树；                                          

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root!=null){
            if(root.val<=Math.max(p.val,q.val)&&root.val>=Math.min(p.val,q.val)){
                return root;
            } else if (root.val<=Math.min(p.val,q.val)){
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }
```

**时间复杂度：**

时间复杂度：O(n)，最理想状态下的时间复杂度为O(logn)（满二叉树），最坏情况下为O(n)（退化为链表）

空间复杂度：O(1)

## 剑指 Offer 68 - II. 二叉树的最近公共祖先

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheClosestCommonAncestorOfABinaryTree.java)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)

 

**示例 1:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```

**示例 2:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```

 

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉树中。

### 题解

#### 存储父节点

递归遍历左子树与右子树，使用哈希表存储节点对应的父节点，自底向上访问p/q的父节点，用集合存储访问路径，再自底向上访问q/p，访问节点中最先出现在集合的，即为要找的最近公共祖先

```java
    Map<Integer,TreeNode> map = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public void dfs(TreeNode root){//存储父节点
        if (root.left!=null){
            map.put(root.left.val,root);
            dfs(root.left);
        }
        if (root.right!=null){
            map.put(root.right.val,root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p!=null){//存储访问路径
            visited.add(p.val);
            p = map.get(p.val);
        }
        while (q!=null){
            if(visited.contains(q.val)){
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(n)

#### 递归

对于root为根节点的二叉树，若root==p或root==q，则root即为最近公共祖先，若root为null，则不存在最近公共祖先。

对于以root为根节点的子树，若左子树没有p或q，则p和q在右子树中，最近公共祖先就是先遍历的那个；若左子树有p或q，右子树没有，则最近公共祖先为左子树先遍历的那个；若左子树与右子树都存在p或q，则root即为最近公共祖先

```java
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right,p,q);
        if(left==null) return right;
        if(right==null) return left;
        return root;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(n)

## 面试题13. 机器人的运动范围

### 题目描述

[原题链接](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheRangeOfMotionOfTheRobot.java)

地上有一个m行n列的方格，从坐标 `[0,0]` 到坐标 `[m-1,n-1]` 。一个机器人从坐标 `[0, 0] `的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

**示例 1：**

```
输入：m = 2, n = 3, k = 1
输出：3
```

**示例 2：**

```
输入：m = 3, n = 1, k = 0
输出：1
```

**提示：**

- `1 <= n,m <= 100`
- `0 <= k <= 20`

### 题解

#### 广度优先搜索

可以使用队列来存储可达位置，每次循环取出一个位置，对于一个位置只需要考虑向右走和向下走，向左或者向上肯定会重复。向右走和向下走同样也会出现重复，可以使用标记数组来记录已经出现的位置

```java
    public int movingCount(int m, int n, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        visited[0][0]=true;
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0],y=poll[1];
            count++;
            if(x+1<m&&!visited[x+1][y]&&check(x+1,y,k)){//向右走
                queue.add(new int[]{x+1,y});
                visited[x+1][y] = true;
            }
            if(y+1<n&&!visited[x][y+1]&&check(x,y+1,k)){//向下走
                queue.add(new int[]{x,y+1});
                visited[x][y+1] = true;
            }
        }
        return count;
    }

    public boolean check(int a,int b,int k){//判断坐标位数和与k的关系
        int count = 0;
        while (a>0){
            count+=a%10;
            a/=10;
        }
        while (b>0){
            count+=b%10;
            b/=10;
        }
        return count <= k;
    }
```

**复杂度分析：**

时间复杂度：O(mn)

空间复杂度：O(mn)

#### 递推

对于标记数组`visited[i][j]`，如果i，j位数和不大于k且 位置`(i-1,j)`或`(i,j-1)`有一处可达，则`(i,j)`也可达，可以利用这个递推关系来编写代码

```java
    public int movingCount2(int m, int n, int k){
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int count = 1;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if((i==0&&j==0)||check2(i)+check2(j)>k){
                    continue;
                }
                if(i-1>=0){
                    visited[i][j] |= visited[i-1][j];//从(i-1,j)向右移动抵达(i,j)
                }
                if(j-1>=0){
                    visited[i][j] |= visited[i][j-1];//从(i,j-1)向下移动抵达(i,j)
                }
                count+=visited[i][j]?1:0;
            }
        }
        return count;
    }
    public int check2(int a){
        int count = 0;
        while (a>0){
            count+=a%10;
            a/=10;
        }
        return count;
    }
```

**复杂度分析：**

时间复杂度：O(mn)

空间复杂度：O(mn)
