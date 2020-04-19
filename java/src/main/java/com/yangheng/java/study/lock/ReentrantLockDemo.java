package com.yangheng.java.study.lock;

import java.util.concurrent.locks.Lock;

/**
 * 可重入锁（递归锁）
 * 外层锁中，仍能获得该锁的代码（简记：锁中锁）
 */
public class ReentrantLockDemo {
    Lock locks = new java.util.concurrent.locks.ReentrantLock();
    public static void main(String[] args) {
        ReentrantLockDemo lock = new ReentrantLockDemo();
        //1.使用synchronized实现
         lock.synchronizedDo();
        //2.使用lock实现
        lock.lockDo();
    }

    private  void lockDo() {
      this.m3();
    }

    private  void synchronizedDo() {
        this.m1();
    }

    public synchronized void m1(){
        System.out.println("------m1()------");
        m2();
    }

    public synchronized void m2(){
        System.out.println("------m2()-------");
    }

    public  void m3(){
        locks.lock();
        try{
            System.out.println("------m3()------");
            m4();
        }finally {
            locks.unlock();
        }

    }

    public void m4(){
        locks.lock();
        try {
            System.out.println("------m4()-------");
        }finally {
            locks.unlock();
        }
    }
}
