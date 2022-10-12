package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : Z 字形变换 https://leetcode.cn/problems/zigzag-conversion/
 * @create :2022-10-12 19:11:00
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(new ZigzagConversion().convert2("PAYPALISHIRING",4));
    }

    /**
     * 我们从下往上一层一层的来看
     * 对于字符串"PAYPALISHIRING"行数为 3 时，排列结果如下
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 第三层即P   A   H   N 中P与A之间，A与H之间，H与N之间均有3个元素，
     * 第二层即A P L S I I G 中AP为一组，LS为一组，II为一组，G一人一组，两个之间均有1个元素
     * 第一层即Y   I   R     中两两之间没有元素
     * 注：之间的元素个数按照字符串的顺序来记录
     * 我们可以对这个排列结果进行一个分组，如下
     * P        A        H      N
     * A P  +   L S  +   I I +  G
     * Y        I        R
     *
     * AP，LS，II之间相差的元素相同，均为1，2*n-1（n为层数），每一层都是这样
     *
     * 也就是说我们只用知道第一行元素的位置，后面的元素就可以类推出来
     * 可以有这样一个数组 flag 来记录第一行元素的位置，位置我们可以通过循环来确定（从0开始，每次加2*numRows -2，当然不能超过字符串长度）
     * 我们一层一层的来遍历（n——>1）,每一层结束flag数组值都需要加1，即从PAHN ——> ALIG
     * 遍历flag集合添加其后面的第一个字符（根据上面分组：A后面是P，L后面是S，I后面是I，G后面没有元素），同样可以通过flag[index]+2*i-2来确定
     * 依次类推
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows<=1){
            return s;
        }
        StringBuilder result = new StringBuilder();
        int num = s.length()%(2*numRows-2)==0? s.length()/(2*numRows-2) : s.length()/(2*numRows-2)+1;//求第一层元素的个数来确定数组大小
        int[] flag = new int[num];//初始化数组
        num = 0;
        for (int i = 0; i < s.length(); i+=2*numRows -2) {
            result.append(s.charAt(i));//记录第一层位置
            flag[num++] = i;
        }
        for ( num = numRows-1; num >0 ; num--) {
            for (int index = 0; index < flag.length; index++) {
                flag[index]+=1;//从下一层开始
                if(flag[index]>=s.length()){//越界可能下一层还有元素，不能直接返回
                    break;
                }
                result.append(s.charAt(flag[index]));//添加数组元素
                if(flag[index]+2*num-2<s.length()&&num!=1){
                    result.append(s.charAt(flag[index]+2*num-2));//添加数组元素后的第一个元素，如果是第一层或者越界就不用添加
                }
            }
        }
        return result.toString();
    }

    /**
     * 优化一下：
     * 去掉flag数组，因为这个数组我们可以通过层数来计算出来
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if(numRows<=1){
            return s;
        }
        StringBuilder result = new StringBuilder();
        for (int num = numRows; num >0 ; num--) {
            for (int index = numRows-num; index < s.length(); index+=2*numRows -2) {//flag数组的迭代
                result.append(s.charAt(index));
                if(num!=numRows&&num!=1&&index+2*num-2<s.length()){//判断是否存在第一个元素
                    result.append(s.charAt(index+2*num-2));
                }
            }
        }
        return result.toString();
    }
}
