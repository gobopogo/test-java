package com.misslyr.test.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author missli
 * @Description
 *
 * 1、SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为
 * static，必须加锁，或者使用 DateUtils 工具类
 *
 * 2、如果是 JDK8 的应用，可以使用 Instant 代替 Date， LocalDateTime 代替 Calendar，
 * DateTimeFormatter 代替 SimpleDateFormat，官方给出的解释： simple beautiful strong immutable
 * thread-safe
 *
 * @Date 2021/4/16 10:10
 **/
public class DateTime {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");

    private static final int MAX_THREAD = 8;
    private static final int MAX_COUNT = 10000;

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10000));
        final CountDownLatch countDownLatch = new CountDownLatch(MAX_COUNT);
        for (int a=0;a<MAX_COUNT;a++){
            pool.execute(() -> {
                //System.out.println(sdf.parse("2021-04-16 10:30:12"));
                // sdf.parse 底层调用原理：
                // SimpleDateFormat --> subParse()--> numberFormat.parse() --> digitList.fitsIntoLong() 中的 --count;
                // 操作了DigitList的成员变量count;(线程不安全)导致 digitList.getLong()时抛出异常
                // pos.index = start; 这个index是ParsePosition类的成员变量 也破坏了线程安全
                // calb.establish(calendar); 该函数对SimpleDateFormat的成员变量calendar进行了cal.clear();操作 也破坏了线程安全
                System.out.println(sdf.format(new Date()));
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println("转换日期完成！");

        Instant it =Instant.now();
        System.out.println(it.toString());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(new Date().getTime());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt.minusDays(1));
        System.out.println(ldt.plusDays(1));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSSS");
        System.out.println(ldt.format(dtf));

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
