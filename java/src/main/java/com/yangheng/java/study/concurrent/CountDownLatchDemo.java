package com.yangheng.java.study.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 一种同步帮助，它允许一个或多个线程等待，直到在其他线程中执行的一组操作完成为止
 *
 * 需求：必须全部的同学走了，班长才能锁门走人。
 * 套路：
 *   1.CountDownLatch countDownLatch = new CountDownLatch(7); 初始化设置计数器
 *   2.countDownLatch.countDown(); 减1
 *   3.countDownLatch.await();  线程阻塞
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        //初始化计数器，并设置初始值是7
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <= 7 ; i++) {
            //创建线程，并开启线程
            Thread thread = new Thread(new School(countDownLatch,i), "t" + i);
            thread.start();
        }
        try {
            //导致当前线程等待，直到锁存器(计数7)递减至零为止，除非该线程被中断，否则后面的main线程会先执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("班长锁门走了...");
    }
}


//线程实现
class School implements Runnable{

    private CountDownLatch countDownLatch ;
    private Integer count ;

    public School(CountDownLatch countDownLatch,Integer count){
        this.count = count ;
        this.countDownLatch = countDownLatch ;
    }

    @Override
    public void run() {
        System.out.println("第"+count+"同学走出教室...");
        //减少锁存器的计数，如果计数达到零，则释放所有等待线程。这个countDown必须在线程的执行体中使用，即
        //业务做完后再减一，防止业务出错了，计数器还在减一
        countDownLatch.countDown();
    }
}


