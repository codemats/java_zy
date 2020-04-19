package com.yangheng.java.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 手写线程池---目的：只要线程能复用即可，没有拒绝策略等复杂复杂功能
 */
public class ThreadPool {

    public static void main(String[] args) {
        ThreadPoolServices services = new ThreadPoolServices(7);
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            services.submit(()->{
                System.out.println(Thread.currentThread().getName()+"\t我在执行任务："+ finalI);
            });
        }
        services.shutDown();
    }
}

class ThreadPoolServices{

    private static List<Thread> threadList = new ArrayList<>();
    private static BlockingQueue<Runnable> blockingQueue ;
    private static boolean flag =  true ;

    public ThreadPoolServices(int threadNum){
        blockingQueue = new LinkedBlockingQueue<>() ;
        //线程初始化
        init(threadNum);
    }

    /**
     * 线程初始化
     * @param threadNum
     */
    private void init(int threadNum){
        if(threadNum <= 0){
            throw new IllegalArgumentException("参数threadNum="+threadNum);
        }

        try {
            for (int i = 1; i <= threadNum; i++) {
                Thread thread = new Thread(new RunThread(), "线程服务" + i);
                thread.start();
                System.out.println(thread.getName() + "\t 初始化成功...");
                threadList.add(thread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程提交
     */
    public void submit(Runnable runnable){
        if(runnable == null){
            throw new NullPointerException("参数runnable不能为空");
        }
        blockingQueue.offer(runnable);
    }

    /**
     * 线程池关闭
     */
    public void shutDown(){
        while (blockingQueue.size() != 0){};
        this.flag = false ;
        System.out.println(Thread.currentThread().getName()+"线程池关闭");
    }

    /**
     * 线程执行器
     */
    class RunThread implements Runnable{
        @Override
        public void run() {
            while(ThreadPoolServices.flag){
                if(ThreadPoolServices.blockingQueue !=null && ThreadPoolServices.blockingQueue.size() > 0){
                    Runnable runnable = blockingQueue.poll();
                    if(runnable != null){
                        runnable.run();
                    }
                }
            }
        }
    }
}




