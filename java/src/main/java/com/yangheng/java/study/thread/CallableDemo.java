package com.yangheng.java.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 线程操作有返回值
 *
 * FutureTask的构造函数，需要callable接口实例
 * FutureTask<String> futureTask = new FutureTask<>(new CallableService());
 *
 * FutureTask实现Runnable接口
 * Thread thread = new Thread(futureTask,"T1");
 *
 * 需要线程操作的业务类可实现Callable<线程返回类型></>接口，@Override call()方法
 * class CallableService implements Callable<String>
 *
 * 注意：
 *   如果使用该方式，想启动多个线程，就必须new 多个futureask才可以，即new Thread(futureTask,"线程名称")，否则
 *   只要是一个futureask，不管ew Thread(futureTask,"线程名称")，只是一个线程工作。
 *
 * 执行demo结果：
 * CallableService操作成功...
 * 线程thread的执行结果：Success
 *
 */
public class CallableDemo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallableService());
        Thread thread = new Thread(futureTask,"T1");
        thread.start();

        try {
            //获取线程执行结果
            System.out.println("线程thread的执行结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 线程操作资源业务
 */
class CallableService implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("CallableService操作成功...");
        return "Success";
    }
}
