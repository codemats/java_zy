package com.yangheng.java.study.concurrent;

/**
 * 并发编程死锁及定位分析
 * 1.jps -l 查看获得运行类的进程编号
 *    150305 com.yangheng.java.study.concurrent.DealLockDemo
 *
 * 2.jstack 150305  使用jstack + 进程编号
 *
 * 3.Found 1 deadlock 尾部发现即为死锁
 *
 * 4.查看发生死锁代码块
 *  "t2":
 *         at com.yangheng.java.study.concurrent.ThreadService.run(DealLockDemo.java:34)
 *         - waiting to lock <0x00000000ebb474c0> (a java.lang.String)
 *         - locked <0x00000000ebb474f8> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 * "t1":
 *         at com.yangheng.java.study.concurrent.ThreadService.run(DealLockDemo.java:34)
 *         - waiting to lock <0x00000000ebb474f8> (a java.lang.String)
 *         - locked <0x00000000ebb474c0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 */
public class DealLockDemo {
    public static void main(String[] args) {
        new Thread(new ThreadService("lockA","lockB"),"t1").start();
        new Thread(new ThreadService("lockB","lockA"),"t2").start();
    }
}


class ThreadService implements Runnable{

    private String lockA ;
    private String lockB ;

    public ThreadService(String lockA,String lockB){
        this.lockA = lockA ;
        this.lockB = lockB ;
    }

    @Override
    public void run() {
       synchronized (this.lockA){
           System.out.println(Thread.currentThread().getName()+"---11111111111");
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (this.lockB){
               System.out.println(Thread.currentThread().getName()+"---22222222222");
           }
       }
    }
}
