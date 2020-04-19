package com.yangheng.java.study.thread;

/**
 * 守护线程,线程被设置成为setDaemon(true)是守护线程，只要启动守护线程的线程停止后，守护线程立即停止
 */
public class ThreadDaemon {
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(()->{
            System.out.println("t1线程run运行....");
            Thread thread2 = new Thread(()->{
                System.out.println("我是一个守护线程,执行任务完成");
            },"DaemonThread");

            //必须在线程启动之前调用此方法
            thread2.setDaemon(true);
            thread2.start();
        },"t1");
        thread1.start();
        //模拟thread1执行的任务，目的是希望thread2(守护线程)执行
        Thread.currentThread().sleep(1000L);
    }
}
