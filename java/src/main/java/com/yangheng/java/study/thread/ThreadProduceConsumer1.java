package com.yangheng.java.study.thread;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.TimeUnit;

/**
 *消费者和生产者 例子:生产者+1 消费者消费当前值
 */
public class ThreadProduceConsumer1 {
    public static void main(String[] args) throws Exception{
      Movie movie = new Movie();

      Thread product = new Thread(()->{
          while (true){
              try {
                  movie.product();
                  TimeUnit.SECONDS.sleep(1);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      },"product");

        Thread consumer = new Thread(()->{
            while(true){
                try {
                    movie.consumer();
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"consumer");

        product.start();
        consumer.start();
    }
}

//资源  synchronized保证数据是同步的，防止多线程消费数据异常
class Movie {

    private Integer count = 0 ;
    private boolean flag = false ;

    /**
     * 生产者++1
     * @throws Exception
     */
    public synchronized void product() throws Exception{
        while(flag){
            this.wait();
        }
        count = count + 1;
        System.out.println("Movie生产了数据:"+count);
        flag = true ;
        this.notifyAll();
    }

    /**
     * 消费当前值
     * @throws Exception
     */
    public synchronized void consumer() throws Exception{
        while(!flag){
            this.wait();
        }
        System.out.println("Movie消费了数据:"+count);
        flag = false ;
        this.notifyAll();
    }

}
