package com.yangheng.java.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞队列实现生产者和消费者
 */
public class ThreadProduceConsumer2 {

    public static void main(String[] args) throws Exception {
        Movie2 movie2 = new Movie2();
            new Thread(()->{
                while(true){
                    movie2.product();
                }
            },"produce").start();

            new Thread(()->{
                while(true){
                    movie2.consumer();
                }
            },"consumer").start();
    }
}

class Movie2 {
    private BlockingQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    private Integer count = 0 ;
    /**
     * 生产者++1
     * @throws Exception
     */
    public void product() {
        try {
            TimeUnit.SECONDS.sleep(1);
            count = count + 1;
            synchronousQueue.put(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Movie生产了数据:"+count);
    }

    /**
     * 消费当前值
     * @throws Exception
     */
    public  void consumer(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Movie消费了数据:"+synchronousQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
