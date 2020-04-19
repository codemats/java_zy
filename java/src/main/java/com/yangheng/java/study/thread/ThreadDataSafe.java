package com.yangheng.java.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程数据安全
 */
public class ThreadDataSafe {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        Thread thread1 = new Thread(new StationService1(ticket),"票售1号");
        Thread thread2 = new Thread(new StationService1(ticket),"票售2号");
        Thread thread3 = new Thread(new StationService1(ticket),"票售3号");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

/**
 *synchronized  多线程访问数据同步
 *   1.在方法添加 public synchronized void SellingTickets()
 *   2.在方法实现体
 *    public  void SellingTickets(){
 *         synchronized (lock){
 *             if(count <= 0 ){
 *                 System.out.println("票售完...");
 *                 throw  new RuntimeException();
 *             }
 *             System.out.println(Thread.currentThread().getName()+"\t 秒票"+num);
 *             num += 1;
 *             count = count-1;
 *         }
 *     }
 * Lock 多线程访问数据同步
 *  private Lock lock = new ReentrantLock();
 *     //卖票
 *     public  void SellingTickets(){
 *         lock.lock();
 *         try{
 *             if(count <= 0 ){
 *                 System.out.println("票售完...");
 *                 throw  new RuntimeException();
 *             }
 *             System.out.println(Thread.currentThread().getName()+"\t 秒票"+num);
 *             num += 1;
 *             count = count-1;
 *         }finally {
 *            lock.unlock(); //这里最后必须释放锁
 *         }
 *     }
 */
class Ticket{
    private Integer count = 100 ;
    private Integer num = 1;
    private Lock lock = new ReentrantLock();
    //卖票
    public  void SellingTickets(){
        lock.lock();
        try{
            if(count <= 0 ){
                System.out.println("票售完...");
                throw  new RuntimeException();
            }
            System.out.println(Thread.currentThread().getName()+"\t 秒票"+num);
            num += 1;
            count = count-1;
        }finally {
           lock.unlock();
        }
    }
    /*private Object lock = new Object();
    //卖票
    public  void SellingTickets(){
        synchronized (lock){
            if(count <= 0 ){
                System.out.println("票售完...");
                throw  new RuntimeException();
            }
            System.out.println(Thread.currentThread().getName()+"\t 秒票"+num);
            num += 1;
            count = count-1;
        }
    }*/
}

//车站售票点1
class StationService1 implements Runnable{
    private Ticket ticket ;
    public StationService1(Ticket ticket ){
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(true){
            ticket.SellingTickets();
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//车站售票点2
class StationService2 implements Runnable{

    private Ticket ticket ;
    public StationService2(Ticket ticket ){
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(true){
            ticket.SellingTickets();
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//车站售票点3
class StationService3 implements Runnable{
    private Ticket ticket ;
    public StationService3(Ticket ticket ){
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(true){
            ticket.SellingTickets();
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
