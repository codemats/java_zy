package com.yangheng.java.study.gc;

public class GcRoots {
    public static void main(String[] args) {
        /**
         * java中可作为GCRoots的对象
         * 1. 虚拟机栈（java栈）
         * 2. 本地方法栈
         * 3. 方法区的常量
         * 4. 方法区静态属性
         *
         *
         * 已GCRoots作为起点做对象引用可达性分析，可达为关联，不可达为垃圾，GC要回收
         */
    }
}

/**
 * java栈（虚拟机栈）
 * 本地方法栈
 * 方法区静态属性
 * 方法区常量
 */
