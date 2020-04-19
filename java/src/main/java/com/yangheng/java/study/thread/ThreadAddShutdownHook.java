package com.yangheng.java.study.thread;


import java.util.concurrent.TimeUnit;

/**
 * 程序异常退出或者正常执行完成后正常的退出前需要做一些事情
 */
public class ThreadAddShutdownHook {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        //客户线程 执行完成关闭执行 虚拟机关闭挂钩
        Thread emailThread = new Thread(new Email(),"email");
        runtime.addShutdownHook(emailThread);

        Thread runThread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 已经开始工作了");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"客户线程");
        runThread.start();
    }
}


class Email implements Runnable{

    @Override
    public void run() {
        System.out.println("发送邮件已经通知了.....");
    }
}
