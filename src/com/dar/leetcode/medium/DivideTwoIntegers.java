package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 两数相除 https://leetcode.cn/problems/divide-two-integers/
 * @create :2022-10-25 10:15:00
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
//        System.out.println(new DivideTwoIntegers().divide(100,-6));
        System.out.println(new DivideTwoIntegers().divide3(2147483647,1));
    }



    /**
     * 倍增思想
     * 累加除数来达到被除数，可以从累加的次数中得到商
     * 25/5=5 ——> 5+5+5+5+5=25
     * 采用倍增的思想来减少循环次数（在原来的基础上同时进行叠加）
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;//极端的情况，因为Integer.MIN_VALUE+Integer.MAX_VALUE=-1
        boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);//判断结果符号
        //全部采用负数来进行计算，因为采用+0来表示0，所以-0可以用来表示其它数字
        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;
        int ans = 0;
        while (dividend <= divisor){//一个加法过程
            int c = divisor, count = -1;//记录次数
            while ( c >= dividend - c){//利用倍增思想进行叠加
                c += c; count += count;
            }
            dividend -= c;//减去前面记录数所对应的数值
            ans += count;//添加记录数
        }
        return flag ? ans : -ans;
    }

    /**
     * 二分查找 + 倍增思想
     * 这里使用了long型来进行转换与越界判断
     * 对于被除数dividend与除数divisor，如果它们都是正整数
     * 则dividend/divisor结果范围应该为[0,dividend]；这是一个有序的数列
     * 我们可以通过二分查找的方式去寻找商，找到最终结果我们需要做两件事：
     * 一是判断mid(中间值)*divisor与dividend的大小关系来移动左右指针
     *      <p>
     *          由于不允许使用乘法、除法和 mod 运算符，我们只能使用“加法”来实现乘法运算
     *          普通的去加肯定不行，所以采用倍增的思想去实现乘法
     *          具体介绍在README.md
     *      </p>
     * 二是确定最终结果
     *      <p>
     *          我们可以采取使用右指针来记录最终的结果，为此我们就需要“偏袒”右指针，使循环结束，右指针记录这最终结果
     *          mid = (left+right+1)>>1，>>1相当与除以2，>>运算优先级没有+高，这里是为了避免混淆才加了一个括号
     *          这样每一次中间值mid会偏向右方（有效数列为偶数时才有效，如1 2 3 4会选取3）
     *          左指针只需要移动到mid就行，移到下一位反而不对，我们只关心恰好mid*divisor小于/等于dividend时mid的值
     *          右指针需要移动到mid的前一位，我们必须保证mid*divisor不能比dividend大
     *      </p>
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2(int dividend, int divisor) {
        long dividend2 = dividend,divisor2 = divisor;//使用long型处理数据，防止越界
        boolean flag = (dividend2 > 0 && divisor2 < 0) || (dividend2 < 0 && divisor2 > 0);//判断结果符号
        if (dividend2 < 0) dividend2 = -dividend2;//转正
        if (divisor2 < 0) divisor2 = -divisor2;//转正
        long left = 0, right = dividend2;//左右指针
        while (left<right){
            long mid = (left+right+1)>>1;//取中间值
            if(mul(mid,divisor2)<=dividend2)//比较
                left = mid;
            else
                right = mid - 1;
        }
        right = flag?-right:right;
        if(right>=Integer.MIN_VALUE&&right<=Integer.MAX_VALUE)
            return (int) right;
        return Integer.MAX_VALUE;
    }
    /**
     * 利用倍增思想使用加法 计算 a * b
     * @param a
     * @param k
     * @return
     */
    long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }

    /**
     * 思路与上面一样 但不使用long
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public int divide3(int dividend, int divisor) {
        /**
         * 先处理一些特殊情况
         */
        if(dividend==0){
            return 0;
        }
        //被除数为最小值
        if(dividend==Integer.MIN_VALUE){
            if(divisor==-1){
                return Integer.MAX_VALUE;
            }
            if(divisor==1){
                return Integer.MIN_VALUE;
            }
        }
        //除数为最小值
        if(divisor==Integer.MIN_VALUE){
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        /**
         * 一般情况
         */
        boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);//判断结果符号
        if (dividend > 0) dividend = -dividend;//转负
        if (divisor > 0) divisor = -divisor;//转负
        int left = 1,right = dividend==Integer.MIN_VALUE?Integer.MAX_VALUE:-dividend,result=0;//选取左右边界（注意越界）
        while (left<=right){
            int mid = left + ((right-left)>>1);//取中间值（防止越界）
            if(check(mid,divisor,dividend)){//mid*divisor>=dividend
                result = mid;
                if(mid==Integer.MAX_VALUE){//防止越界（特殊情况2147483647/1）
                    break;
                }
                left = mid+1;//正数*负数，正数越大，负数越小
            } else {
                right = mid-1;//正数*负数，正数越小，负数越大
            }
        }
        return flag?-result:result;
    }

    /**
     * 判断mid*divisor与dividend的大小关系
     * @param mid 待定的商 >0
     * @param divisor 除数 <0
     * @param dividend 被除数 <0
     * @return mid*divisor大于/等于dividend返回true
     */
    private boolean check(int mid, int divisor, int dividend) {
        int result = 0;//记录累加的结果
        while (mid!=0){
            if((mid&1)==1){
                if(result<dividend-divisor){//负数只会越加越小，只要出现了result+divisor比dividend小，大小关系就已经确定
                    return false;
                }
                result += divisor;
            }
            if(mid!=1){
                if(divisor<dividend-divisor){//道理与上面相同
                    return false;
                }
                divisor+=divisor;
            }
            mid>>=1;
        }
        return true;
    }


}
