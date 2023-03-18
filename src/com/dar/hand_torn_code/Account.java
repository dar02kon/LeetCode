package com.dar.hand_torn_code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :wx
 * @description :
 * @create :2023-03-16 16:15:00
 */
public class Account {
    private int id;
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // 顺序加锁
    public void transfer1(Account to, double money){
        if(this.id<to.getId()){
            synchronized (this){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to){
                    operation(this,to,money);
                }

            }
        } else if(this.id>to.id){
            synchronized (to){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this){
                    operation(this,to,money);
                }
            }
        } else {

        }
    }

    //显示锁
    private final Lock lock = new ReentrantLock();
    private Lock getLock() {
        return lock;
    }
    // 尝试拿锁
    public void transfer2(Account to, double money){
        while(true) {
            if(this.getLock().tryLock()) {
                try {
                    if(to.getLock().tryLock()) {
                        try {
                            //两把锁都拿到了
                            operation(this,to,money);
                            break;
                        }finally {
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    this.getLock().unlock();
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void operation(Account from, Account to, double money){
        if(from.getBalance()<money){
            return;
        }
        from.setBalance(from.getBalance()-money);
        to.setBalance(to.getBalance()+money);
    }
}
