package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 最长回文子串 https://leetcode.cn/problems/longest-palindromic-substring/
 * @create :2022-10-11 15:51:00
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abab"));
    }

    /**
     * 回文串是左右对称（对称轴可能是字符，也可能是两个字符之间）
     *
     * 初始化一个对称轴变量（可以用循环时的字符下标来代替）
     * 设置两变量left,right记录对称轴左右字符下标，比较对称轴左右字符直到不相同或者越界
     * 比较结束后，right-left-1>maxLen，更新最长回文串下标值
     *
     * <p>
     *     对称轴可能是字符，也可能是两个字符之间
     *     所以需要进行循环依次需要比较两次
     *     第一次以下标i的字符为对称轴进行比较
     *     第二次以下标为i的字符与下标为i+1的字符中间为对称轴进行比较
     * </p>
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i-1;
            int right = i+1;
            while (left>=0&&right<s.length()){
                if(s.charAt(left)==s.charAt(right)){
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if(right-left-1>maxRight-maxLeft-1){
                maxLeft = left;
                maxRight = right;
            }
            left = i;
            right = i+1;
            while (left>=0&&right<s.length()){
                if(s.charAt(left)==s.charAt(right)){
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if(right-left-1>maxRight-maxLeft-1){
                maxLeft = left;
                maxRight = right;
            }
        }
        return s.substring(maxLeft+1,maxRight);
    }

    //下面这些解答来自评论区
    /**
     * 动态规划
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        char[] cs = s.toCharArray();
        // dp[i][j]:表示s[i][j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：单独一个字符肯定是回文子串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 经验：dp区域是正方形的话，通常左下角区域无效不需要再填，因为走过的区域不用再走
        for (int j = 1; j < len; j++) { // 上三角区域，按列从上到下填
            for (int i = 0; i < j; i++) {
                // 首尾不相等时，必不是回文串
                if (cs[i] != cs[j]) {
                    dp[i][j] = false;
                } else {
                    // 首尾相等时，有2种情况
                    // 情况1：s[i...j]长度不超过3，不用检查必为回文串
                    // 情况2：s[i...j]长度大于3，由s[i+1...j-1]来判断
                    dp[i][j] = j - i + 1 <= 3 || dp[i + 1][j - 1];
                }
                // 更新max和begin
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // manachar方法
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 将s转换为加了特殊字符#的字符数组，目的是统一奇偶数的回文中心差异性问题
        // 比如：s=”cabac“转化为cs=[#c#a#b#a#c#]
        char[] cs = manacherString(s, len);
        // pArr[i]是cs[i]每个位置的最大回文半径
        // 比如：cs=[#c#a#b#a#c#]，pArr=[1,2,1,2,1,6,1,2,1,2,1]
        int[] pArr = new int[cs.length];
        // pR是cs[i]每个位置的回文右边界的下一个位置
        // 比如：cs=[#c#a#b#a#c#]，pR=1,3,3,5,5,11(此时pR第一次遍历完cs，之后的pR可以不再更新),11,11,11,11,11
        int pR = -1;
        // index是最近更新pR时的回文中心位置
        // 比如：cs=[#c#a#b#a#c#]，index=0,1,1,3,3,5(之后pR不再更新，index也不再更新),5,5,5,5,5
        int index = -1;
        // max记录pArr[i]中最大值：pArr[i]最大值就能算出原字符串的最长回文子串长度
        int maxLen = Integer.MIN_VALUE;
        int centerIndex = Integer.MIN_VALUE;
        int begin = 0;
        for (int i = 0; i != cs.length; i++) {
            // 第一句代码:每轮循环时,i至少不需要验证的区域,先给到pArr[i],解释如下:
            // pR<=i:i超过了pR，无法优化，不用验证的区域就是pArr[i]本事=回文半径为1
            // pR>i:i没有超过pR，可以优化，至少不需要验的区域：Math.min(pArr[2 * index - i], pR - i)
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            // 第二句代码:在i位置尝试往外扩最长回文半径长度pArr[i]:
            // 如果扩成功pArr[i]++;否则立刻停止扩的过程break
            while (i + pArr[i] < cs.length && i - pArr[i] >= 0) {
                if (cs[i + pArr[i]] == cs[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            // 每轮循环,扩的长度超过回文右边界下一个位置，就更新pR和index
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
        }
        // 找出cs中回文半径最大值maxLen和其对应的数组索引centerInter
        for (int i = 0; i < cs.length; i++) {
            if (pArr[i] > maxLen) {
                maxLen = pArr[i];
                centerIndex = i;
            }
        }
        // 根据cs中回文半径和对应坐标算原字符串中的最大回文长度和最大回文中心
        // 原字符串最大回文长度：maxLen-1，比如#a#b#a#，b的回文半径=4，那么原aba的最长回文子串长度为3
        maxLen = maxLen - 1;
        // 原字符串最大回文串中心：(centerIndex - 1)/2，比如#a#b#a#，b的centerIndex=4；那么原aba的b的坐标为(4-1)/2
        centerIndex = (centerIndex - 1) / 2;
        // 根据centerIndex和maxLen算最大回文串begin下标
        // 奇数：centerIndex-maxLen/2
        // 偶数：centerIndex-maxLen/2+1
        // 统一：centerIndex-(maxLen-1)/2
        begin = centerIndex - (maxLen - 1) / 2;
        return s.substring(begin, begin + maxLen);
    }

    // 将str转换成带#号的字符数组:解决奇数、偶数中心往外扩的差异性
    public static char[] manacherString(String s, int n) {
        char[] charArr = s.toCharArray();
        int index = 0;// index遍历charArr
        // s:a -> res:#a#，长度1 -> 3，奇数位放#，偶数位放原字符
        // s:ab -> res:#a#b#，长度2 -> 5，奇数位放#，偶数位放原字符
        // s:aba -> res:#a#b#a#，长度3 -> 7，奇数位放#，偶数位放原字符
        // 长度变化规律:len -> len+len+1=len*2+1，奇数位放#，偶数位放原字符
        char[] res = new char[n * 2 + 1];
        for (int i = 0; i != res.length; i++) {
            // 偶数位放#，奇数位放原字符
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

}
