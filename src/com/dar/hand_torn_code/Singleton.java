package com.dar.hand_torn_code;

import java.io.Serializable;

/**
 * @author :wx
 * @description :
 * @create :2023-03-16 15:39:00
 */
public class Singleton implements Serializable {

    // volatile修饰以防止指令重排序
    private volatile static Singleton singleton = null;

    private Singleton() {
        if (singleton != null) {
            throw new RuntimeException("Can not do this");
        }
    }

    public Singleton getSingleton() {
        // 进入方法内，先判断实例是否为空，以确定是否需要进入同步代码块
        if (singleton == null) {
            synchronized (Singleton.class) {
                // 进入同步代码块时再次判断实例是否为空
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    // 定义readResolve方法，防止反序列化返回不同的对象
    private Object readResolve() {
        return singleton;
    }

}
