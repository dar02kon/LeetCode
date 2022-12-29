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
