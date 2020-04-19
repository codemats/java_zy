package com.yangheng.java.study.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用-----形同虚设，get()返回null，在gc之前做相关操作，gc后对象会放入一个引用队列
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {

        Object o1  = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,queue);

        o1 = null ;
        System.out.println("gc之前o1："+o1);
        System.out.println("gc之前："+phantomReference.get());
        System.gc();
        System.out.println("gc之后o1："+o1);
        System.out.println("gc之后："+phantomReference.get());
        System.out.println("gc之后queue："+queue.poll());

    }
}
