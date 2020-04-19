package com.yangheng.java.study.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁
 * 公平锁：先来后到获取资源
 * 非公平锁：插队抢夺资源，吞吐量大
 */
public class LockDemo {
    public static void main(String[] args) {

        //非公平锁 new ReentrantLock()  为空或者是false
        //公平锁 new ReentrantLock(true)
        Lock lock = new ReentrantLock();

    }

    //synchronized 典型的非公平锁
    public synchronized void sayHello(){

    }
}
