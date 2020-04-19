package com.yangheng.java.study.stackerror;

/**
 * 栈溢出错误
 * Exception in thread "main" java.lang.StackOverflowError
 */
public class StackErrorDemo {
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        m1();
    }
}
