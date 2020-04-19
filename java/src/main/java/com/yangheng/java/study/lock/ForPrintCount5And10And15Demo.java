package com.yangheng.java.study.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock条件循环打印：
 * 需求：线程需要按照顺序执行
 *   A线程打印5次，B线程打印10次，C线程打印15次，来10回
 * A打印....1
 * A打印....2
 * A打印....3
 * A打印....4
 * A打印....5
 * B打印....1
 * B打印....2
 * B打印....3
 * B打印....4
 * B打印....5
 * B打印....6
 * B打印....7
 * B打印....8
 * B打印....9
 * B打印....10
 * C打印....1
 * C打印....2
 * C打印....3
 * C打印....4
 * C打印....5
 * C打印....6
 * C打印....7
 * C打印....8
 * C打印....9
 * C打印....10
 * C打印....11
 * C打印....12
 * C打印....13
 * C打印....14
 * C打印....15
 */
public class ForPrintCount5And10And15Demo {

    private static int num = 1;
    public static void main(String[] args) {

        PrintThreadResource resource = new PrintThreadResource();

        Lock lock = new ReentrantLock();

        //精确唤醒需要唤醒的线程
        Condition A = lock.newCondition();
        Condition B = lock.newCondition();
        Condition C = lock.newCondition();

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                lock.lock();
                try{
                    while(num != 1){
                        A.await();
                    }
                    resource.print5();
                    num = 2;
                    B.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"A").start();

            new Thread(()->{
                lock.lock();
                try{
                    while(num != 2){
                        B.await();
                    }
                    resource.print10();
                    num = 3;
                    C.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"B").start();
            new Thread(()->{
                lock.lock();
                try{
                    while(num != 3){
                        C.await();
                    }
                    resource.print15();
                    num = 1;
                    A.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"C").start();
        }
    }
}

/**
 * 资源
 */
class PrintThreadResource{

    public void print5(){
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()+"打印...."+i);
        }
    }

    public void print10(){
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()+"打印...."+i);
        }
    }

    public void print15(){
        for (int i = 1; i <= 15; i++) {
            System.out.println(Thread.currentThread().getName()+"打印...."+i);
        }
    }
}
