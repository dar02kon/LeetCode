package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 二进制求和 https://leetcode.cn/problems/add-binary/
 * @create :2022-09-23 16:48:00
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1010","1011"));
    }

    /**
     * 使用变量count来记录进位，创建StringBuilder对象来存储最终的结果
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int count = 0;
        StringBuilder s = new StringBuilder();
        while (i > -1 || j > -1){
            int result;
            if(i<=-1){//字符串a已经遍历完
                result = Integer.parseInt(b.charAt(j)+"") + count;
            } else if(j<=-1){//字符串b已经遍历完
                result = Integer.parseInt(a.charAt(i)+"")  + count;
            } else {//对应位相加
                result = Integer.parseInt(a.charAt(i)+"") + Integer.parseInt(b.charAt(j)+"") + count;
            }
            if(result>=2){//result可能为0，1，2，3
                count = 1;
                s.insert(0, result-1);
            } else {
                count = 0;
                s.insert(0,result);
            }
            --i;
            --j;
        }
        if(count==1){
            s.insert(0,1);
        }
        return s.toString();
    }

    /**
     * 优化一下：
     * 使用遍历carry来记录对应位相加后的结果
     * carry/2即为进位
     * carry % 2为本位添加到StringBuilder
     * 如果循环结束carry>0，则需要在末尾添加1（反转后字符串首位为1）
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;//a若已经遍历完，则记为0
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;//b若已经遍历完，则记为0
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

}
