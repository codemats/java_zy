package com.yangheng.java.study;


import java.util.concurrent.TimeUnit;

/**
 * 生产环境CPU占用过高，怎么办？-----真实的查看的还是栈中数据，即最后使用jstack查看导致CPU的标高的线程
 * 1.jps -l 查看当前运行项目的代码，记下pid
 * 2.top 查看当前1步骤的pid，再查看CPU的使用率
 * 3.ps -mp pid -o THREAD,tie,time查看导致CPU标高的具体pid(简称P)
 *       m:线程
 *       p：CPU时间
 * 4.把P的值转换成16进制的值，简称(H)
 * 5.jstack pid | grep H -A60
 *
 * 结果：
 *  yangheng@yangheng:~/IdeaProjects/java$ jstack 16053 | grep 3eb9 -A60   命令
 * "main" #1 prio=5 os_prio=0 tid=0x00007fc34400c000 nid=0x3eb9 runnable [0x00007fc34b095000]
 *    java.lang.Thread.State: RUNNABLE
 *         at java.io.FileOutputStream.writeBytes(Native Method)
 *         at java.io.FileOutputStream.write(FileOutputStream.java:326)
 *         at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)
 *         at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)
 *         - locked <0x00000000c2e268e0> (a java.io.BufferedOutputStream)
 *         at java.io.PrintStream.write(PrintStream.java:482)
 *         - locked <0x00000000c2e18998> (a java.io.PrintStream)
 *         at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:221)
 *         at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:291)
 *         at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:104)
 *         - locked <0x00000000c2e18940> (a java.io.OutputStreamWriter)
 *         at java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:185)
 *         at java.io.PrintStream.write(PrintStream.java:527)
 *         - locked <0x00000000c2e18998> (a java.io.PrintStream)
 *         at java.io.PrintStream.print(PrintStream.java:669)
 *         at java.io.PrintStream.println(PrintStream.java:806)
 *         - locked <0x00000000c2e18998> (a java.io.PrintStream)
 *         at com.yangheng.java.study.TestMain.main(TestMain.java:9)----项目中的代码，即为有问题的代码，查看上下
 *         代码是否有问题
 *
 */
public class CPUBGMain {
    public static void main(String[] args) throws InterruptedException {
        while(true){
            System.out.println("haha...");
        }
    }
}
