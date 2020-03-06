package com.sw.test.time;

import java.util.concurrent.TimeUnit;

/**
 * @description: TimeUnitTest
 * @author: shaowei
 * @date: 2020-03-06 14:36
 */
public class TimeUnitTest {

    /*
    TimeUnit.DAYS          //天
    TimeUnit.HOURS         //小时
    TimeUnit.MINUTES       //分钟
    TimeUnit.SECONDS       //秒
    TimeUnit.MILLISECONDS //毫秒
    */

    /*
    public long toMillis(long d)    //转化成毫秒
    public long toSeconds(long d)  //转化成秒
    public long toMinutes(long d)  //转化成分钟
    public long toHours(long d)    //转化成小时
    public long toDays(long d)     //转化天
    */
    public static void main(String[] args) {
        //1天有24个小时    1代表1天：将1天转化为小时
        System.out.println(TimeUnit.DAYS.toHours(1));
        //结果： 24
        //1小时有3600秒
        System.out.println(TimeUnit.HOURS.toSeconds(1));
        //结果3600
        //把3天转化成小时
        System.out.println(TimeUnit.HOURS.convert(3, TimeUnit.DAYS));
        //结果是：72

        try {
            // 睡眠方式
            TimeUnit.SECONDS.sleep( 5 ); // ==  Thread.sleep( 5 * 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
