package com.yangheng.java.study.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁），共享锁（读锁）
 * 独占锁（写锁）：只能被一个线程持有---->生活：李四在填写员工培训签到表,    new ReentrantReadWriteLock().writeLock()
 * 共享锁（读锁）：可能被多个线程持有---->生活：李三，王五在看员工培训签到表  ew ReentrantReadWriteLock().readLock()
 *
 * 需求：手写缓存，set()缓存操作是写锁，get()缓存是读锁
 * 结果：
 * t1	缓存放入数据key：name,数据值value:李四
 * t2	缓存放入数据key：age,数据值value:25
 * t3	缓存放入数据key：school,数据值value:北京大学
 * t4	缓存获取数据key：name,数据值value:李四
 * t5	缓存获取数据key：age,数据值value:25
 * t6	缓存获取数据key：school,数据值value:北京大学
 * t6	缓存获取数据key：name,数据值value:李四
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        new Thread(()->{
            cache.set("name","李四");
        },"t1").start();

        new Thread(()->{
            cache.set("age","25");
        },"t2").start();

        new Thread(()->{
            cache.set("school","北京大学");
        },"t3").start();

        new Thread(()->{
            cache.get("name");
        },"t4").start();

        new Thread(()->{
            cache.get("age");
        },"t5").start();

        new Thread(()->{
            cache.get("school");
            cache.get("name");
        },"t6").start();


    }
}

//缓存
class MyCache{
    private Map<String,Object> cache = new ConcurrentHashMap<>();
    //读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void set(String key ,Object value){
        if (key == null || value == null) throw new NullPointerException("参数错误");
        lock.writeLock().lock();
        try{
            cache.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t缓存放入数据key："+key+",数据值value:"+value);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key){
        if (key == null) throw new NullPointerException("参数错误");
        if (cache.isEmpty()) return null;
        Object value = null;
        lock.readLock().lock();
        try{
            value = cache.get(key);
            System.out.println(Thread.currentThread().getName()+"\t缓存获取数据key："+key+",数据值value:"+value);
        }finally {
            lock.readLock().unlock();
        }
        return value ;
    }

    public void clean(){
        cache.clear();
    }
}

