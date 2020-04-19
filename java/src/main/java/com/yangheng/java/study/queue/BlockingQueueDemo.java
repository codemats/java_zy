package com.yangheng.java.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列---FIFO(先进先出)，先到先得
 * 阻塞体现：
 *    1.队列中没有数据时，获取数据阻塞（get）
 *    2.队列中数据满时，放数据阻塞（set）
 *
 *  常用队列有3中，操作都是一样：
 *  1.ArrayBlockingQueue 数据(初始值时必须设定大小)
 *  2.LinkedBlockingDeque 连表(默认大小Integer.MaxValue)
 *  3.SynchronousQueue 单个元素，不会存储元素【同步队列没有任何内部容量，甚至没有一个容量】
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{

        //测试SynchronousQueue
        BlockingQueue<String> queue = new SynchronousQueue<>();

        //放入数据
        new Thread(()->{
            try {

                for (int i = 0; i <10 ; i++) {
                    queue.put( i+"");
                    System.out.println("---------");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        TimeUnit.SECONDS.sleep(1);
        //获取数据
        new Thread(()->{
            try {
                System.out.println(queue.size());//0 同步队列没有任何内部容量，甚至没有一个容量
                for (int i = 0; i < 10; i++) {
                    System.out.println(queue.take());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();

    }
    public static void arrayBlockingQueue() throws Exception{
        int size = 100 ;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(size);


        for (int i = 0; i < size; i++) {
            blockingQueue.offer(i+"");
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < size; i++) {
            System.out.println(blockingQueue.poll());
        }

        System.out.println(blockingQueue.peek());//只检索队列的头部是否有元素，null为空
        System.out.println(blockingQueue.size());
    }
}
