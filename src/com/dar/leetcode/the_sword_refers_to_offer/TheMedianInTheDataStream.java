package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.*;

/**
 * @author :wx
 * @description : 剑指 Offer 41. 数据流中的中位数 https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/?favorite=xb9nqhhg
 * @create :2022-12-09 09:29:00
 */
public class TheMedianInTheDataStream {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        medianFinder.addNum(-5);
        double median = medianFinder.findMedian();
        System.out.println(median);
    }


}

/**
 * 维护一个集合，每次获取中位数前都需要排序
 */
class MedianFinder {
    private final List<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }
    public void addNum(int num) {
        list.add(num);
    }
    public double findMedian() {
        Collections.sort(list);
        int size = list.size() % 2 == 0 ? list.size() / 2 - 1 : list.size() / 2;
        return list.size() % 2 == 0 && size < list.size() - 1 ? (list.get(size) + list.get(size + 1)) / 2.0 : list.get(size);
    }
}

/**
 * 优先队列
 */
class MedianFinder2 {
    PriorityQueue<Integer> minQueue;
    PriorityQueue<Integer> maxQueue;

    public MedianFinder2() {
        minQueue =  new PriorityQueue<Integer>((a,b)->(b-a));
        maxQueue =  new PriorityQueue<Integer>((a,b)->(a-b));
    }
    public void addNum(int num) {
        if(minQueue.isEmpty()||num<=minQueue.peek()){
            minQueue.offer(num);
            if(minQueue.size()>maxQueue.size()+1){
                maxQueue.offer(minQueue.poll());
            }
        } else {
            maxQueue.offer(num);
            if(minQueue.size()<maxQueue.size()){
                minQueue.offer(maxQueue.poll());
            }
        }

    }
    public double findMedian() {
        return minQueue.size()<=maxQueue.size()?(minQueue.peek()+maxQueue.peek())/2.0:minQueue.peek();
    }
}