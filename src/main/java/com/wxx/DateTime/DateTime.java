package com.wxx.DateTime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateTime {
    public static void main(String[] args) {
        localDate();
        //localDateTime();
        //zonedDateTime();
    }

    public static void localDateTime() {
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("Month: [" + month + "] day: [" + day + "] seconds: [" + seconds + "]");

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        //12 december 2014
        LocalDate date3 = LocalDate.of(2012, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    public static void zonedDateTime() {
        // Get the current date and time
        ZonedDateTime date6 = ZonedDateTime.parse("2012-12-12T10:15:30+05:30[Asia/Tokyo]");
        System.out.println("date6: " + date6);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);
    }

    public static void localDate() {
        //LocalDate final类型 一旦赋值不可改变
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);
        //进行一次重新赋值　未报错　但值也未改变
        localDate.of(2008, 8, 8);
        System.out.println("localDate: " + localDate);
        //获取一个日期　方式一
        LocalDate localDate1 = LocalDate.of(2008, 8, 8);
        System.out.println("localDate1: " + localDate1);
        //获取一个日期　方式二
        LocalDate localDate2 = LocalDate.parse("2008-08-09");
        System.out.println("localDate2: " + localDate2);

        //获取明天日期
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("tomorrow: " + tomorrow);

        //获得当前减去一个月的日期(它接受一个枚举时间单位)
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay: " + previousMonthSameDay);

        //得到一个日期并获取其是一周中的周几
        DayOfWeek saturday = LocalDate.parse("2018-07-28").getDayOfWeek();
        System.out.println("saturday: " + saturday);

        //得到一个日期并获取其是一个月中的哪一天
        int twelve = LocalDate.parse("2018-07-28").getDayOfMonth();
        System.out.println("twelve: " + twelve);

        //当前年份是否是闰年
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println("leapYear: " + leapYear);

        //两个时间的关联关系
        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.now());
        System.out.println("2016-06-12 notBefore: " + notBefore);
        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.now());
        System.out.println("2016-06-12 isAfter: " + isAfter);

        //时间边界 获取某个日期的起始时间　如(2016-06-12　00:00:00)
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        System.out.println("beginningOfDay: " + beginningOfDay);
        //时间边界 获取某个日期的当月的起始天　如(2016-06-12的那个月第一天是2016-06-01)
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth: " + firstDayOfMonth);
    }
}
