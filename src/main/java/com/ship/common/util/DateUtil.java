package com.ship.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/2/27.
 * 日期工具类
 */
public class DateUtil {

    private static  final  String yyyMMdd = "yyyy-MM-dd";

    private static  final  String hhmmss = "yyyy-MM-dd HH:mm:ss";

    private static TimeZone tz = TimeZone.getTimeZone("GMT+8");



    /**
     * 当前系统时间戳
     * @return
     */
    public static long getMillisecond() {
        long millisecond = 0;
        TimeZone.setDefault(tz);
        Calendar cal = Calendar.getInstance();
        millisecond = cal.getTimeInMillis();
        return millisecond;
    }

    public static  Date getDate(){
        return  new Date();
    }



    /**
     * 获取当前系统时间
     * @return
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(getMillisecond());
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(yyyMMdd);
        return  sdf.format(date);
    }


    public static String formatDateHHmmss(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(hhmmss);
        return  sdf.format(date);
    }


    public static Date dayTime(int day){
        Date date =  getDate();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(12, -day * 24 * 60);
        return ca.getTime();

    }

    /**
     * 字符串日期转Date
     * @param dateStr
     * @return
     */
    public static Date getDate_str(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(hhmmss);
        if ("".equals(dateStr)) {
            dateStr = sdf.format(DateUtil.getDate());
        }
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {


        System.out.print(formatDateHHmmss(dayTime(1)));



    }



}
