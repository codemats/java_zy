package com.yangheng.java.study.threadpool;

import java.util.concurrent.*;

/**
 * 线程池--避免线程上下文切换，线程复用
 *
 * Executors.newFixedThreadPool(初始值：线程数);
 *  固定线程数
 * Executors.newSingleThreadExecutor();
 * 单一线程
 * Executors.newCachedThreadPool();
 * 缓存，多线程可扩容，能做就做，不能做就扩容
 *
 * 线程提交：
 * 1.交线程是有返回值
 * executorService.submit();
 *
 * 2.提交线程是没有返回值
 * executorService.execute();
 *
 * 例子执行结果：
 * pool-1-thread-1	 进来...
 * pool-1-thread-2	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-1	 进来...
 * pool-1-thread-3	 进来...
 *
 * 注意：实际开发过程中以上提到的3中模式都不用，默认队列是都是Integer.MaxValue，上产上都是new ThreadPoolExecutor()设置数值使用
 * 类   public ThreadPoolExecutor(int corePoolSize,核心线程数-------银行长期工作窗口
 *                               int maximumPoolSize,最大线程数（包含corePoolSize)--------银行一共有多少窗口
 *                               long keepAliveTime,多余线程存活的时间数值---------加班窗口(当前加班窗口未工作)多久关闭时常
 *                               TimeUnit unit,多余线程存活的时间数值单位--------加班窗口(当前加班窗口未工作)多久关闭时常单位
 *                               BlockingQueue<Runnable> workQueue,任务队列，提交但未执行-------银行等待叫号的休息区座位数
 *                               ThreadFactory threadFactory, 线程工厂，一般默认即可------银行属性默认即可
 *                               RejectedExecutionHandler handler) 拒绝策略，表示任务队列满之后，是否还要接受外部请求-----休息区座位满后，银行是否继续接待用户的策略
 *
 * 拒绝策略：表示任务队列满之后的行为
 *    AbortPolicy（默认）直接抛出异常 Exception in thread "main" java.util.concurrent.RejectedExecutionException:
 *    CallerRunsPolicy 任务回调到调用者-----一般线程池不能在处理后，都是main线程来处理
 *    DiscardOldestPolicy 抛弃队列中等待最久的任务
 *    DiscardPolicy  直接抛弃
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //测试固定线程数
        //testNewFixedThreadPool();

        //测试任务线程满之后，默认的拒绝策略是什么？new AbortPolicy() 直接抛出注入异常
        testBlockingQueueGt();
    }

    public static void testBlockingQueueGt(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,6,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1)
        );

        for (int i = 1; i <= 90 ; i++) {
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"进来...");
            });
        }
        executor.shutdown();
        //结果：报错
        //Exception in thread "main" java.util.concurrent.RejectedExecutionException:
    }
    public static void testNewFixedThreadPool(){
        //创建固定线程数为3的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        /**
         * public static ExecutorService newFixedThreadPool(int nThreads) {
         *         return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         *     }
         */
        for (int i = 1; i <= 10 ; i++) {
            //提交线程
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t 进来...");
            });
        }
        executorService.shutdown();
    }
}
