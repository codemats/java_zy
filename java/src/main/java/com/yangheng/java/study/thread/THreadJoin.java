package com.yangheng.java.study.thread;

/**
 * 控制线程执行任务顺序
 */
public class THreadJoin {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main线程启动....");
        Thread thread1 = new Thread(new ThreadRunnable1(),"t1");
        Thread thread2 = new Thread(new ThreadRunnable2(),"t2");
        Thread thread3 = new Thread(new ThreadRunnable3(),"t3");

        thread1.start();
        thread2.start();
        thread3.start();

        //线程执行的顺序是t1--t2---t2
        thread1.join();//等待t1线程死亡
        thread2.join();//等待t2线程死亡
        thread3.join();//等待t3线程死亡
        System.out.println("main线程结束");
    }
}



class ThreadRunnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("ThreadRunnable...1.....");
    }
}


class ThreadRunnable2 implements Runnable{

    @Override
    public void run() {
        System.out.println("ThreadRunnable...2.....");
    }
}


class ThreadRunnable3 implements Runnable{

    @Override
    public void run() {
        System.out.println("ThreadRunnable...3.....");
    }
}
