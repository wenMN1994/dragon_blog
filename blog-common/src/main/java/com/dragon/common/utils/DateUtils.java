package com.dragon.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * 
 * @author DragonWen
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static Logger log = LoggerFactory.getLogger(DateUtils.class);
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String STARTTIME = " 00:00:00";
    public static final String ENDTIME = " 23:59:59";
    public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    public final static String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};
    public final static String SPLIT_STRING = "(中国标准时间)";

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取现在的时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formate.format(date);
    }

    /**
     * 获取几天之后的日期
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @param day  加减的天数
     * @return
     */
    public static Date getDate(String date, int day) {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Date beforeDate = formate.parse(date);
            cal.setTime(beforeDate);
            cal.add(Calendar.DAY_OF_MONTH, day);
            Date newDate = cal.getTime();
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date 转换成  String
     *
     * @param dateTime
     * @return
     */
    public static String dateTimeToStr(Date dateTime) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(dateTime);
    }

    /**
     * 获取某个时间段内所有日期
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDayBetweenDates(String begin, String end) {
        Date dBegin = strToDateTime(begin);
        Date dEnd = strToDateTime(end);
        List<String> lDate = new ArrayList<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 将  String 转换成  date
     *
     * @param dateTime
     * @return
     */
    public static Date strToDateTime(String dateTime) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
}
