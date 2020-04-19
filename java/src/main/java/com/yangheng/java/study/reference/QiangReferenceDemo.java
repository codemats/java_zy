package com.yangheng.java.study.reference;

/**
 * 强引用---OOM都不收
 */
public class QiangReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();//强引用
        Object o2 = o1 ;
        o1 = null ;
        System.out.println("GC之前o1:"+o1);
        System.out.println("GC之前o2:"+o2);
        //GC
        System.gc();
        System.out.println("GC之后o1:"+o1);
        System.out.println("GC之后o2:"+o2);
    }
}
