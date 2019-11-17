package com.cn.systemnanotime;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cord on 2018/5/7.
 */
public class SystemApiPerfTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 100;
        /**并发*/
        long interval = concurrentTest(count, ()->{System.nanoTime();});
        System.out.format("[%s] thread concurrent test <nanoTime> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        /**串行循环*/
        interval = serialNanoTime(count);
        System.out.format("[%s] count serial test <nanoTime> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");

        /**并发*/
        interval = concurrentTest(count, ()->{System.currentTimeMillis();});
        System.out.format("[%s] thread concurrent test <currentTimeMillis> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        /**串行循环*/
        interval = serialCurrentTime(count);
        System.out.format("[%s] count serial test <currentTimeMillis> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

    }

    private static long concurrentTest(int threads, final Runnable r) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    start.await();
                    try {
                        r.run();
                    }finally {
                        end.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        long stime = System.nanoTime();
        start.countDown();
        end.await();
        return System.nanoTime() - stime;
    }

    private static long serialNanoTime(int count){
        long stime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            System.nanoTime();
        }
        return System.nanoTime() - stime;
    }

    private static long serialCurrentTime(int count){
        long stime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        return System.nanoTime() - stime;
    }
}

/*测试结果如下:
https://www.cnblogs.com/jpfss/p/9674057.html
[100] thread concurrent test <nanoTime> cost total time [5085539]ns, average time [50855]ns.
[100] count serial test <nanoTime> cost total time [2871]ns, average time [28]ns.
-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
[100] thread concurrent test <currentTimeMillis> cost total time [7678769]ns, average time [76787]ns.
[100] count serial test <currentTimeMillis> cost total time [4103]ns, average time [41]ns.

串行情况下耗时趋于稳定，但是在并行情况下就不一样了。

因为这两个api都是native方法，涉及到系统层级的调用，与平台底层实现有关。

​ 其实在串行情况下这两个api其实性能很好，但是在并发情况下回急剧下降，原因在于计时器在所有进程之间共享，并且其还一直在发生变化，当大量线程尝试同时去访问计时器的时候，就涉及到资源的竞争，于是也就出现并行效率远低于串行效率的现象了。所以在高并发场景下要慎重使用System.nanoTime()和System.currentTimeMillis()这两个API。

附加资料:

linux上使用的计时器一般有两种: TSC, HPET

HPET计时器(HPET Timer)：高精度事件计时器，也是外部硬件计时器，固定频率14.31818MHz。

TSC计时器(TSC Timer)：时间戳计数计时器，是基于硬件的计时器，但频率可变。以前它就等于处理器频率，在早些年不是问题，但后来处理器不断加入会降低频率的扩展频谱、电源管理等功能，就有问题了，于是后来设计的时候将其改为和处理器频率相独立。

HPET的性能相对TSC的性能要低

(注: 等级越高的时钟越容易被系统使用)


等级	1 ~ 99	100 ~ 199	200 ~ 299	300 ~ 399	400 ~ 499
特点	非常差的时钟源，只能作为最后的选择。如 jiffies	基本可以使用但并非理想的时钟源。如 PIT	正确可用的时钟源。如 ACPI PM Timer，HPET	快速并且精确的时钟源。如 TSC	理想时钟源。如 kvm_clock，xen_clock
时钟源相关操作:

查看当前系统可用时钟源
# cat /sys/devices/system/clocksource/clocksource0/available_clocksource
查看当前使用的时钟源
# cat /sys/devices/system/clocksource/clocksource0/current_clocksource
修改时钟源
# echo 'hpet' > /sys/devices/system/clocksource/clocksource0/current_clocksource
http://pzemtsov.github.io/2017/07/23/the-slow-currenttimemillis.html

http://blog.sina.com.cn/s/blog_71d9aee40101gtuv.html

https://blog.csdn.net/dymloveyxp1314/article/details/10065223

http://news.mydrivers.com/1/273/273867_all.htm
*/