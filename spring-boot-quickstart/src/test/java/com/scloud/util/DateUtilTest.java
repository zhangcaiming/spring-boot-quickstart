package com.scloud.util;

import com.scloud.AbstractCommonJunitTest;
import org.junit.Test;

import java.time.*;

/**
 * @author andy
 * @create 2018/8/19 21:43
 **/
public class DateUtilTest extends AbstractCommonJunitTest {


    /**
     * yyyy-MM-dd
     */
    @Test
    public void testLocalDate(){
        LocalDate currentDate = LocalDate.now();
        System.out.println("currentDate:" + currentDate);

        LocalDate firstDay_2015 = LocalDate.of(2015, Month.AUGUST, 1);
        System.out.println("Specific Date=" + firstDay_2015);

        LocalDate hunderDay2015 = LocalDate.ofYearDay(2015, 100);
        System.out.println("100th day of 2015=" + hunderDay2015);

    }

    /**
     * hh:mm:ss.zzz
     */
    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);

        //Creating LocalTime by providing input arguments
        LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day=" + specificTime);
    }

    /**
     * yyyy-MM-dd-HH-mm-ss.zzz
     */
    @Test
    public void testLocalDateTime() {
        //Current Date
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Current DateTime=" + today);

        //Current Date using LocalDate and LocalTime
        today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("Current DateTime=" + today);

        //Creating LocalDateTime by providing input arguments
        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
        System.out.println("Specific Date=" + specificDate);
    }

    @Test
    public void testTimestampForInstant() {
        //Current timestamp
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = " + specificTime);

        //Duration example
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay);
    }


}
