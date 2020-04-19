package com.yangheng.java.study.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 计数信号量。从概念上讲，信号量维护一组许可证。acquire()如果需要，每个块都会阻塞，
 * 直到获得许可为止，然后获得许可。每个都release()添加一个许可证，有可能释放阻止收购者
 *
 * 套路：
 *  1.Semaphore semaphore = new Semaphore(初始值); 创建Semaphore具有给定数量的许可证和不公平公平设置的
 *  2.semaphore.acquire();//从此信号量获取许可，阻塞直到可用，否则线程被中断
 *  3.semaphore.release();//释放许可证，将其返回到信号灯。
 *需求：车库有3个车位，6辆车枪车位，每两车停留2秒钟后离开车库，其他车继续抢夺车位
 * 结果：
 * 车辆编码：编号3进入车库...
 * 车辆编码：编号1进入车库...
 * 车辆编码：编号2进入车库...
 * 车辆编码：编号3休息2秒后离开车库...
 * 车辆编码：编号1休息2秒后离开车库...
 * 车辆编码：编号2休息2秒后离开车库...
 * 车辆编码：编号4进入车库...
 * 车辆编码：编号5进入车库...
 * 车辆编码：编号6进入车库...
 * 车辆编码：编号4休息2秒后离开车库...
 * 车辆编码：编号5休息2秒后离开车库...
 * 车辆编码：编号6休息2秒后离开车库...
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //车辆
        Integer carCount = 6 ;

        //车库车位
        Integer carGarage = 3 ;

        Semaphore semaphore = new Semaphore(carGarage);

        for (int i = 1; i <= carCount ; i++) {
            new Thread(new ParkingPlace(semaphore),"编号"+i).start();
        }
    }

}

//抢车位
class ParkingPlace implements Runnable{

    private Semaphore semaphore ;

    public ParkingPlace(Semaphore semaphore){
        this.semaphore = semaphore ;
    }

    @Override
    public void run() {
        try {
            String formatIn = "车辆编码：%s进入车库...";
            semaphore.acquire();//从此信号量获取许可，阻塞直到可用，否则线程被中断
            System.out.println(String.format(formatIn,Thread.currentThread().getName()));
            TimeUnit.SECONDS.sleep(2);

            semaphore.release();//释放许可证，将其返回到信号灯。
            String formatOut = "车辆编码：%s休息2秒后离开车库...";
            System.out.println(String.format(formatOut,Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
