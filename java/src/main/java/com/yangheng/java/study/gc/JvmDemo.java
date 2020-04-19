package com.yangheng.java.study.gc;

import java.util.concurrent.TimeUnit;

/**
 * JVM参数分为3种
 * 1.标准   JDK1.0起就存在的参数
 *
 * 2.x -Xcomp：编译
 *
 * 3.XX
 *   3.1 boolean类型
 *       -XX:+参数  注意：+表示开启  -表示关闭  例如：-XX：+PrintGcDetail 打印详细GC日志信息
 *   3.2 key—value类型
 *       -XX：属性key=value 例如：XX：MaxDirectMemorySize = 1m 设置新I / O（java.nio程序包）直接缓冲区分配的最大总大小1M
 *
 * 4.查看系统默认参数
 *   1.jps -l 找到运行类的进程编号
 *   2.jinfo -flags 进程编号
 *
 *   例如查看到的参数：
 *       Non-default VM flags: -XX:CICompilerCount=2 -XX:InitialHeapSize（-Xms）=65011712 -XX:MaxHeapSize(-Xmx)=1025507328 -XX:MaxNewSize=341835776
 *       -XX:MinHeapDeltaBytes=524288 -XX:NewSize=21495808 -XX:OldSize=43515904 -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC
 *       Command line:  -XX:+PrintGCDetails -javaagent:/home/yangheng/soft/idea-IU-201.6668.121/lib/idea_rt.jar=45395:/home/yangheng/soft/idea-IU-201.6668.121/bin -Dfile.encoding=UTF-8
 *
 * 5.设置参数：
 *   -Xms == -XX:InitialHeapSize=设定的数值(默认系统内存X(1/64))---kv
 *   -Xmx == -XX:MaxHeapSize=设定的数值(默认系统内存X(1/4))--------kv
 *
 *  6.查看系统参数
 *      6.1系统默认参数 java -XX:+PrintFlagsInitial
 *      6.2系统最后参数 java -XX:+PrintFlagsFinal
 *      注意： bool ClipInlining = true 系统默认值(=)
 *            bool ClipInlining := true 用户修改后最终参数(:=)
 *
 *   7.java -XX:+PrintCommandLineFlags -version
 *     查看命令行参数
 *
 *
 *   8.JVM常用配置参数
 *      -Xmx:最大堆内存(默认1/4 内存)    -Xms：最小堆内存(默认1/64 内存) 可用单位：g，m，k
 *           实际项目中使用的2者一样大，防止GC操作导致内存不稳定
 *      -Xss：单线程栈的大小，等价 -XX：ThreadStackSize=数值
 *      -Xmn:年轻代大小（一般不调）
 *      -XX：Metaspaceize:元空间大小
 *      -XX：surrivorRation:新生代eden和s0/s1的比例
 *      -XX：NewRation 年轻代和老年代占比  NewRation=4--->老年代：4 年轻代：1
 *      -XX：MaxTenuringhreadshold：设置垃圾最大年龄 默认15
 *
 */
public class JvmDemo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("开始拉...");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
