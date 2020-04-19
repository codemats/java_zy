package com.yangheng.java.study.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁 ----CAS思想实现，利用循环比较方式
 * 例子目的需要实现：3个线程需要一个一个的执行业务，手写自旋锁要生效
 * t1	 获得锁
 * t1	 进来操作业务...
 * t1	 释放锁
 *
 * t2	 获得锁
 * t2	 进来操作业务...
 * t2	 释放锁
 *
 * t3	 获得锁
 * t3	 进来操作业务...
 * t3	 释放锁
 *
 */
public class SpinLock {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                sayHello();
            } finally {
                lock.unLock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                sayHello();
            } finally {
                lock.unLock();
            }
        }, "t2").start();

        new Thread(() -> {
            lock.lock();
            try {
                sayHello();
            } finally {
                lock.unLock();
            }
        }, "t3").start();
    }

    /**
     * 模拟业务代码
     */
    public static void sayHello() {
        System.out.println(Thread.currentThread().getName() + "\t 进来操作业务...");
    }
}


/**
 * 自定义锁
 */
class MyLock {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private volatile Thread thread = new Thread();

    //锁
    public void lock() {
        while (!atomicReference.compareAndSet(null, thread)) {
        };
        System.out.println(Thread.currentThread().getName() + "\t 获得锁");
        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //解锁
    public void unLock() {
        while (!atomicReference.compareAndSet(thread, null)) {
        };
        System.out.println(Thread.currentThread().getName() + "\t 释放锁");
        System.out.println();
        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
