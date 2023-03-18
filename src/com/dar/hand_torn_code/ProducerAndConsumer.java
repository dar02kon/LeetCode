package com.dar.hand_torn_code;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :wx
 * @description :
 * @create :2023-03-16 15:51:00
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        ReenTest<Integer> reenTest = new ReenTest<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    Integer integer = reenTest.get();
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    reenTest.put(new Random().nextInt());
                }
            }).start();
        }
    }
}

class SyncTest<T>{
    private LinkedList<T> linkedList = new LinkedList<>();
    private int max = 1;

    public synchronized void put(T val){
        while (linkedList.size()==max){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.addLast(val);
        // 如果使用notify可能唤醒的是同类型的线程，导致死锁
        this.notifyAll();
    }

    public synchronized T get(){
        while (linkedList.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T first = linkedList.getFirst();
        this.notifyAll();
        return first;
    }
}

class ReenTest<T>{
    private LinkedList<T> linkedList = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    int max = 1;
    Condition putCondition = lock.newCondition();
    Condition getCondition = lock.newCondition();

    public void put(T value){
        lock.lock();
        try {
          while (linkedList.size()==max){
              try {
                  putCondition.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          linkedList.addLast(value);
          getCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        lock.lock();
        try {
            while (linkedList.size()==0){
                try {
                    getCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = linkedList.removeFirst();
            putCondition.signal();
            return first;
        } finally {
            lock.unlock();
        }
    }
}