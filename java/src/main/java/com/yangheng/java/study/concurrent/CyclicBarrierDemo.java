package com.yangheng.java.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 一种同步辅助工具，它允许一组线程全部互相等待以到达一个公共的障碍点
 * CyclicBarriers在涉及固定大小的线程方的程序中很有用，该线程方有时必须互相等待
 * 等待机制：
 * //初始值 new CyclicBarrier(7,()) 7=count  减到0时开始执行任务（command.run()）
 *      int index = --count;
 *             if (index == 0) {  // tripped
 *                 boolean ranAction = false;
 *                 try {
 *                     final Runnable command = barrierCommand;
 *                     if (command != null)
 *                         command.run();
 *                     ranAction = true;
 *                     nextGeneration();
 *                     return 0;
 *                 } finally {
 *                     if (!ranAction)
 *                         breakBarrier();
 *                 }
 *             }
 *
 * 需求：集齐7课龙珠，才能召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //初始化，并设置个线程之和为7，到达7后，初始化的线程才开始工作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("7颗龙珠集齐，可以召唤神龙");
        });

        for (int i = 1; i <= 7 ; i++) {
            new Thread(new DragonBall(i,cyclicBarrier),"t"+i).start();
        }
    }
}

//集齐龙珠的业务
class DragonBall implements Runnable{

    private Integer count ;
    private CyclicBarrier cyclicBarrier ;

    public DragonBall(Integer count ,CyclicBarrier cyclicBarrier){
        this.count = count ;
        this.cyclicBarrier = cyclicBarrier ;
    }

    @Override
    public void run() {
        System.out.println("集齐第"+count+"颗龙珠...");
        try {
            //阻塞线程
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
