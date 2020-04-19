package com.yangheng.java.study.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用---gc时，内存够用不回收，不够用就回收
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        Object[] o = new Object[1*1023];
        SoftReference softReference = new SoftReference(o);
        Object o2 = softReference.get();
        o = null ;
        System.out.println("GC之前o:"+o);
        System.out.println("GC之前o2:"+o2);

        //GC
        System.gc();
        System.out.println("GC之后o:"+o);
        System.out.println("GC之后o2:"+o2);
    }

}
