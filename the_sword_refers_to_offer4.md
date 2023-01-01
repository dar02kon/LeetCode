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
