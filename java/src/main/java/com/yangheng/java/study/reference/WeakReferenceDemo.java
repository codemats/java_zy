package com.yangheng.java.study.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用---只要发生gc，都回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        WeakReference weakReference = new WeakReference(o1);

        o1 = null ;//让GC回收掉
        System.out.println("GC之前o1:"+o1);
        System.out.println("GC之前o2:"+weakReference.get());
        //GC
        System.gc();
        System.out.println("GC之后o1:"+o1);
        System.out.println("GC之后o2:"+weakReference.get());


    }
}
