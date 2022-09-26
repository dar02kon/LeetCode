package com.dar.leetcode.simple;

import java.util.Stack;

/**
 * @author :wx
 * @description : 相同的树 https://leetcode.cn/problems/same-tree/
 * @create :2022-09-26 19:15:00
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,t3,null);
        TreeNode t1 = new TreeNode(1,null,t2);
        System.out.println(new SameTree().isSameTree2(t1,t1));
    }

    /**
     * 中序遍历逐步比较，用两个栈来保存节点位置（也可以用一个栈，注意一下入栈和出栈的顺序就行）
     * 若出现节点对应值不相等或者形状不同则直接返回false
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack_p = new Stack<>();
        Stack<TreeNode> stack_q = new Stack<>();
        boolean flag = true;
        while (p!=null&&q!=null){
            if(p.left!=null&&q.left!=null&&flag){//左子树均不为空且未出现在栈中
                stack_p.add(p);
                stack_q.add(q);
                p = p.left;
                q = q.left;
            } else if((p.left==null&&q.left==null)||(!flag &&p.left!=null&&q.left!=null)){
                if(p.val==q.val){//对应节点的值相等
                    if(p.right!=null&&q.right!=null){//右子树均不为空
                        p = p.right;
                        q = q.right;
                        flag = true;
                    } else if (p.right==null&&q.right==null){//右子树同时为空
                        flag = false;
                        if(!stack_p.empty()&&!stack_q.empty()){
                            p = stack_p.pop();
                            q = stack_q.pop();
                        } else if(stack_p.empty() && stack_q.empty()){//栈空
                            break;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return (q == null || p != null) && (q != null || p == null);
    }




    /**
     * 递归写法
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
       if(p==null&&q==null){
           return true;
       } else if(p==null || q ==null){//只有一个为空
           return false;
       } else if(p.val!=q.val){
           return false;
       } else {
           return isSameTree2(p.left,q.left) && isSameTree2(p.right,q.right);
       }
    }


}
