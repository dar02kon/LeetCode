package com.dar.leetcode.medium;

import java.util.*;

/**
 * @author :wx
 * @description : 括号生成 https://leetcode.cn/problems/generate-parentheses/
 * @create :2022-10-22 13:41:00
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        List<String> list = new GenerateParentheses().generateParenthesis(2);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 暴力解法，求出所有可能的组合
     * 因为每一位都只有两种可能'('或')'，可以列出所有情况
     * 再对每一种情况进行判断
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        getAll(new char[n*2],0,list);
        return list;
    }
    public boolean valid(char[] target){//判断括号使用是否合法
        int count = 0;
        for (char c : target) {
            if(c=='('){
                count++;
            } else {
                count--;
            }
            if(count<0){//从左往右，')'已经多了，即前面没有与它匹配的'('
                return false;
            }
        }
        return count==0;//大于0，说明'('多了，后面没有')'与它匹配
    }
    public void getAll(char[] target,int index,List<String> list){
        if(index==target.length){//字符已经满足位数要求再判断是否合法
            if(valid(target)){
                list.add(new String(target));
            }
        } else {
            target[index]='(';//该为取左括号
            getAll(target,index+1,list);//进行递归
            target[index]=')';//该位取右括号
            getAll(target,index+1,list);//继续递归
        }
    }

    /**
     * 递归回溯
     * 我们可以根据要求来创建字符串
     * 左括号'('的数量在小于n是才添加，大于n就已经没有意义了
     * 从左往右 右括号')'数量在小于左括号'('的数量才添加，大于就没有意义了
     * 确保字符串的长度为2n时一定符合括号匹配的要求
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        getAll2(new StringBuilder(),list,0,0,n);
        return list;
    }
    private void getAll2(StringBuilder s, List<String> list, int left, int right, int n) {
        if(s.length()==2*n){//长度和规则都符号要求
            list.add(s.toString());
        } else {
            if(left<n){//左括号的数量小于n
                s.append('(');//添加左括号
                getAll2(s,list,left+1,right,n);//递归
                s.deleteCharAt(s.length()-1);//回溯后删除添加的左括号，避免对下一步产生影响
            }
            if(right<left){//右括号的数量小于左括号
                s.append(')');//添加右括号
                getAll2(s,list,left,right+1,n);//递归
                s.deleteCharAt(s.length()-1);//回溯后删除添加的右括号，避免对下一步产生影响
            }
        }

    }

    /**
     * 还有一种思路：组装'()'，然后去重
     * 可以这么去理解：
     * <P>
     *     3对括号字符串的可能有：["()()()","()(())","(()())","(())()","((()))"]
     *     拿走一个'()'就会变成2对括号的字符串，但我们不知道拿哪一对'()'，那就都去尝试
     *     最后就会获得所有2对括号的字符串，当然会有重复
     *
     *     反过来也是这个道理，现在有2对括号的所有字符串，我们添加一对'()'就变成了3对
     *     我们不知道往哪添加'()'，那就所有的位置都去尝试，最后对结果进行去重
     * </P>
     * @param n
     * @return
     */
    public static List<String> generateParenthesis3(int n){
        if (n == 1){//初始化
            return Collections.singletonList("()");
        }
        HashSet<String> set = new HashSet<>();//利用HashSet去重
        for (String str : generateParenthesis3(n - 1)){
            for (int i = 0; i <= str.length()/2; i++) {
                set.add(str.substring(0,i) + "()" + str.substring(i,str.length()));//组装字符串，每一个位置都尝试去插入'()'
            }
        }
        return new ArrayList<>(set);

    }


}
