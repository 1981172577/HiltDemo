package com.migrsoft.hiltdemo.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateFormatUtils extends DateUtils {

    // 一分钟的毫秒值
    public static final long ONE_MINUTE = 60 * 1000;

    // 一小时的毫秒值
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    // 一天的毫秒值
    public static final long ONE_DAY = 24 * ONE_HOUR;

    // 一月的毫秒值
    public static final long ONE_MONTH = 30 * ONE_DAY;

    // 一年的毫秒值
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    // 年-月-日 时:分:秒(2014-4-16 19:14:51)
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    //年-月-日 时:分:秒.毫秒(2014-4-16 19:14:51.SSS)
    public static final String PATTERN_PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE_SENCOND = "yyyy-MM-dd HH:mm:ss.SSS";
    // 年-月-日 时:分:秒(2014-4-16 19:14)
    public static final String PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE_NO_SPACE = "yyyy-MM-ddhh:mm:ss";

    // 年-月(2014-4)
    public static final String PATTERN_YEAR_MONTH = "yyyy-MM";

    public static final String PATTERN_YEAR = "yyyy";
    public static final String PATTERN_MONTH = "MM";
    public static final String PATTERN_DAY = "dd";

    public static final String PATTERN_FULL_PINYIN_CONNECTION = "yyyy年MM月dd日   HH:mm:ss";

    public static final String PATTERN_FULL_NO_CONN = "yyyyMMddHHmmssSSS";

    public static final String PATTERN_DATE = "yyyyMMdd";
    public static final String PATTERN_TIME = "HHmmss";

    public static final String PATTERN_HOUR_MONTH = "HH:mm";

    public static final String PATTERN_TIME_MINUTE_SECOND = "HH:mm:ss";

    public static final String PATTERN_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final String PATTERN_YEAR_MONTH_DATE = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_TIME_HOUR_MINUTE = "mm:ss";

    public static final String PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE2 = "yyyy-MM-dd HH:mm";

    public static final String PATTERN_FULL_FOR_RECEIPT = "yyyy/MM/dd HH:mm:ss";

    public static final String YEAR_MONTH_DATE = "MMM d, yyyy K:m:s a";
    /**
     * the milli second of a day
     */
    public static final long DAYMILLI = 24 * 60 * 60 * 1000;
    /**
     * the milli seconds of an hour
     */
    public static final long HOURMILLI = 60 * 60 * 1000;
    /**
     * the milli seconds of a minute
     */
    public static final long MINUTEMILLI = 60 * 1000;
    /**
     * the milli seconds of a second
     */
    public static final long SECONDMILLI = 1000;
    /**
     * added time
     */
    public static final String TIMETO = " 23:59:59";
    /**
     * flag before
     */
    public static final transient int BEFORE = 1;
    /**
     * flag after
     */
    public static final transient int AFTER = 2;
    /**
     * flag equal
     */
    public static final transient int EQUAL = 3;
    /**
     * date format dd/MMM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";
    /**
     * date format dd/MM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";
    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * date format YYYY-MM-DD HH24:MI:SS
     */
    public static final String DB_TIME_PATTERN = "YYYY-MM-DD HH24:MI:SS";
    /**
     * date format dd/MM/yy HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    /**
     * date format dd/MM/yy HH24:mm
     */
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    /**
     * date format yyyyMMddHHmmss
     */
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
    /**
     * date format yyyyMMdd
     */
    public static final String DATE_FMT_0 = "yyyyMMdd";
    /**
     * date format yyyy/MM/dd
     */
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    /**
     * date format yyyy/MM/dd hh:mm:ss
     */

    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    /**
     * date format yyyy-MM-dd
     */
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    /**
     * new a Calendar instance
     */
    static GregorianCalendar cldr = new GregorianCalendar();

    /**
     * set the default time zone
     */
    static {
        cldr.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
    }

    /**
     * 将一个日期字串按照指定模式格式化
     *
     * @param date        日期字串
     * @param fromPattern 日期的原始格式
     * @param toPattern   要显示的格式
     * @return 格式化后的字串
     */
    public static String format(String date, String fromPattern, String toPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern, Locale.getDefault());
        try {
            Date d = sdf.parse(date);
            return format(d, toPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个日期字串按照指定模式格式化
     *
     * @param date    日期
     * @param pattern 要显示的格式
     * @return 格式化后的字串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * "MMM d, yyyy K:m:s a"
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(String date, String pattern) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        String time = "";
        try {
            Date d2 = sdf.parse(date);
            time = DateFormatUtils.format(d2, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @param date
     * @param toPattern
     * @return
     */
    public static Date format2(String date, String toPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        Date d2 = null;
        try {
            d2 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2;
    }

    /**
     * 将一个日期格式的字符串转成Date对象
     *
     * @param date    日期格式的字串
     * @param pattern 字串的格式
     * @return Date对象
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat sdftemp = new SimpleDateFormat(pattern);
            Date dates = sdftemp.parse(date);
            return dates;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse2(String date, String pattern) {
        try {
            SimpleDateFormat sdftemp = new SimpleDateFormat(pattern);
            Date dates = sdftemp.parse(date);
            return dates;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 月，日，时，分，秒1位数前面加0
     *
     * @param value
     * @return string
     */
    public static String convertMDHMS(int value) {
        String result = "";
        if (value < 10) {
            result = "0" + value;
        } else {
            result = "" + value;
        }
        return result;
    }

    /**
     * 与系统时间做比较(>true);(<false)
     *
     * @param time
     * @return
     */
    public static boolean timeCompare(String time) {
        boolean flag = true;
        if (!"".equals(time)) {
            int h = Integer.valueOf(time.split(":")[0]);
            int s = Integer.valueOf(time.split(":")[1]);

            final Calendar c = Calendar.getInstance();
            int mHorse = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            if (h < mHorse) {
                flag = false;
            } else if (h == mHorse) {
                if (s < mMinute) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 与系统当前日期做比较 (>true)(<false);
     *
     * @param ymd
     * @return
     */
    public static boolean dateCompare(String ymd) {
        Date date = new Date();
        Date nowdate = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            date = sdf.parse(ymd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int value = date.compareTo(nowdate);
        return (value > 0);
    }

    /**
     * change date to string
     * 将日期类型的参数转成String类型
     *
     * @return the format string
     */
    public static String toString(Date date) {
        return toString(date, DATE_FMT_3);
    }

    /**
     * change date object to string
     * 将String类型的日期转成Date类型
     *
     * @param dt date object
     * @return the formatted string
     */
    public static String toString(Date dt, String format) {
        return DateFormatUtils.format(dt, format);
    }

    public static String formatDate(Date dt, String format) {
        return toString(dt, format);
    }


    /**
     * 获取Date所属月的最后一天日期
     *
     * @param date
     * @return Date 默认null
     */
    public static Date getMonthLastDate(Date date) {
        if (null == date) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);

        Date lastDate = ca.getTime();
        return lastDate;
    }

    /**
     * 获取Date所属月的第一天日期
     *
     * @param date
     * @return Date 默认null
     */
    public static Date getMonthFirstDate(Date date) {
        if (null == date) {
            return null;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);

        Date firstDate = ca.getTime();
        return firstDate;
    }


    /**
     * 计算两个日期间隔的天数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getIntervalDays(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 计算两个日期间隔的小时数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getTimeIntervalHours(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 60 * 1000));
    }

    /**
     * 计算两个日期间隔的分钟数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getTimeIntervalMins(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 1000));
    }

    /**
     * 比较两个日期的先后顺序
     *
     * @param src
     * @param desc
     * @return
     */
    public static int compareDate(Date src, Date desc) {
        if ((src == null) && (desc == null)) {
            return EQUAL;
        } else if (desc == null) {
            return BEFORE;
        } else if (src == null) {
            return AFTER;
        } else {
            long timeSrc = src.getTime();
            long timeDesc = desc.getTime();

            if (timeSrc == timeDesc) {
                return EQUAL;
            } else {
                return (timeDesc > timeSrc) ? AFTER
                        : BEFORE;
            }
        }
    }

    /**
     * 比较两个日期的先后顺序
     *
     * @param first  date1
     * @param second date2
     * @return EQUAL  - if equal BEFORE - if before than date2 AFTER  - if over than date2
     */
    public static int compareTwoDate(Date first, Date second) {
        if ((first == null) && (second == null)) {
            return EQUAL;
        } else if (first == null) {
            return BEFORE;
        } else if (second == null) {
            return AFTER;
        } else if (first.before(second)) {
            return BEFORE;
        } else if (first.after(second)) {
            return AFTER;
        } else {
            return EQUAL;
        }
    }

    /**
     * 比较日期是否介于两者之间
     *
     * @param date  the specified date
     * @param begin date1
     * @param end   date2
     * @return true  - between date1 and date2 false - not between date1 and date2
     */
    public static boolean isDateBetween(Date date, Date begin, Date end) {
        int c1 = compareTwoDate(begin, date);
        int c2 = compareTwoDate(date, end);

        return (((c1 == BEFORE) && (c2 == BEFORE)) || (c1 == EQUAL) || (c2 == EQUAL));
    }


    /**
     * return current date
     *
     * @return current date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * return current calendar instance
     *
     * @return Calendar
     */
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * return current time
     *
     * @return current time
     */
    public static Date getCurrentDateTime() {
        return new Date(System.currentTimeMillis());
    }

    public static String getCurrentDateTimeString() {
        return toString(new Date(System.currentTimeMillis()), TIME_PATTERN_SESSION);
    }

    /**
     * 获取年份
     *
     * @param date Date
     * @return int
     */
    public static final int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取年份
     *
     * @param millis long
     * @return int
     */
    public static final int getYear(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date Date
     * @return int
     */
    public static final int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月份
     *
     * @param millis long
     * @return int
     */
    public static final int getMonth(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期
     *
     * @param date Date
     * @return int
     */
    public static final int getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取日期
     *
     * @param millis long
     * @return int
     */
    public static final int getDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取小时
     *
     * @param date Date
     * @return int
     */
    public static final int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取小时
     *
     * @param millis long
     * @return int
     */
    public static final int getHour(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Map<String, Integer> getCycleDate(Date startDate, Date endDate) {//TODO
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        return resultMap;
    }

    /**
     * 获取一天中最大的时间
     * 把日期后的时间归0 变成(yyyy-MM-dd 23:59:59)
     *
     * @param date
     * @return
     */
    public static Date getDayLastTime(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取一天中最小的时间
     * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return
     */
    public static Date getDayFirstTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static String getWeek(Date date) {
        String mWay = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        return formartGetWeek(mWay);
    }

    public static String getWeek(String time, String pattern) {
        String mWay = "";
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(parse(time, pattern));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        return formartGetWeek(mWay);
    }

    /**
     * 获取当前星期几  格式：星期一
     *
     * @return
     */
    public static String formartGetWeek(String mWay) {
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "周" + mWay;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return DateFormatUtils.format(new Date(), DateFormatUtils.PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE);
    }

    public static Date getCurrentTime() {
        return DateFormatUtils.parse(DateFormatUtils.format(new Date(), DateFormatUtils.PATTERN_HOUR_MONTH), DateFormatUtils.PATTERN_HOUR_MONTH);
    }

    /**
     * 获取当前星期几
     *
     * @return
     */
    public static int getWeekFlag() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return Integer.valueOf(c.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 格式化日期  格式：从 2015  1 3 变成 2015-01-03
     *
     * @return
     */
    public static String formatDateWithZero(int year, int month, int day) {
        String arg22 = "";
        String arg33 = "";
        month = month + 1;
        if ((month + "").toString().length() == 1) {
            arg22 = "0" + month;
        } else {
            arg22 = month + "";
        }
        if ((day + "").toString().length() == 1) {
            arg33 = "0" + day;
        } else {
            arg33 = day + "";
        }
        return year + "-" + arg22 + "-" + arg33;
    }


    public static Map getWeekDay(int n) {
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, n * 7);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
        map.put("mon", df.format(cal.getTime()));
        print("********得到本周一的日期*******" + df.format(cal.getTime()));
        // 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 增加一个星期，才是我们中国人理解的本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        map.put("sun", df.format(cal.getTime()));
        print("********得到本周天的日期*******" + df.format(cal.getTime()));
        return map;
    }

    public static Map getMonthDate() {
        Map<String, String> map = new HashMap<String, String>();
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        map.put("monthF", format.format(calendar.getTime()));
        print("*********得到本月的最小日期**********" + format.format(calendar.getTime()));
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        // 打印
        map.put("monthL", format.format(calendar.getTime()));
        print("*********得到本月的最大日期**********" + format.format(calendar.getTime()));
        return map;
    }

    /**
     * 获取上月 第一天和最后一天
     *
     * @return
     */
    public static Map getLastMonthDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //获取前月的最后一天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        Map<String, String> map = new HashMap<String, String>();
        map.put("lastMonthF", format.format(calendar.getTime()));
        map.put("lastMonthL", format.format(calendar1.getTime()));

        print("*********得到上月的最大日期**********" + map.get("lastMonthF") + "    " + map.get("lastMonthL"));
        return map;
    }


    private static void print(Object o) {
        System.out.println(o.toString());
    }

    /**
     * 上季度或者下季度  日期
     *
     * @param lastOrCurr 当前季度：true 上季度:false
     * @return
     */
    public static Map getQuarterDate(boolean lastOrCurr) {
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if (lastOrCurr) {
            c.add(Calendar.MONTH, 1);
        } else {
            c.add(Calendar.MONTH, -2);
        }
        int currentMonth = c.get(Calendar.MONTH);
        String start = null;
        String end = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
                c.set(Calendar.DATE, 1);
                start = shortSdf.format(c.getTime());
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
                end = shortSdf.format(c.getTime());
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
                c.set(Calendar.DATE, 1);
                start = shortSdf.format(c.getTime());
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
                end = shortSdf.format(c.getTime());
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
                c.set(Calendar.DATE, 1);
                start = shortSdf.format(c.getTime());
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
                end = shortSdf.format(c.getTime());
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
                c.set(Calendar.DATE, 1);
                start = shortSdf.format(c.getTime());
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
                end = shortSdf.format(c.getTime());
            } else if (currentMonth == 0) {
                if (lastOrCurr) {
                    Calendar startCalendar = Calendar.getInstance();
                    startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
                    startCalendar.set(Calendar.DAY_OF_MONTH, 1);
                    start = shortSdf.format(startCalendar.getTime());

                    Calendar endCalendar = Calendar.getInstance();
                    endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
                    endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = shortSdf.format(endCalendar.getTime());
                } else {
                    Calendar startCalendar = Calendar.getInstance();
                    startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
                    startCalendar.set(Calendar.DAY_OF_MONTH, 1);
                    start = shortSdf.format(startCalendar.getTime());

                    Calendar endCalendar = Calendar.getInstance();
                    endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
                    endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = shortSdf.format(endCalendar.getTime());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("lastQuarteF", start);
        map.put("lastQuarteL", end);
        print("*********得到季度的最大日期**********" + map.get("lastQuarteF") + "    " + map.get("lastQuarteL"));
        return map;
    }

    /**
     * 获取上个季度的时间范围
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Map getLastQuarter() {
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        Map<String, String> map;
        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
            startCalendar.set(Calendar.DAY_OF_MONTH, 1);
            start = shortSdf.format(startCalendar.getTime());

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
            endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = shortSdf.format(endCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        map = new HashMap<String, String>();
        map.put("lastQuarteF", start);
        map.put("lastQuarteL", end);
        return map;
    }

    /**
     * 获取昨天的时间
     *
     * @param date
     * @param fromPattern 输出的日期格式
     * @return
     */
    public static String getYestoday(Date date, String fromPattern) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(fromPattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取前一天的日期
     *
     * @param date
     * @return
     */
    public static Date getYestoday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        return calendar.getTime();
    }


    public static String getStatetime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -7);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    public static String getCheckOrderTime(String time, String fromPattern) {
        String dateString = "";
        try {
            Date date = parse(time, PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.SECOND, 1);
            date = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat(fromPattern);
            dateString = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 比较日期与当前时间大小
     *
     * @param time1 HH:mm
     * @param time2 HH:mm
     * @return true time1>time2
     */
    public static boolean comparTime(String time1, String time2) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        boolean result = false;
        try {
            Date dt1 = df.parse(time1);
            Date dt2 = df.parse(time2);
            if (dt1.getTime() >= dt2.getTime()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 比较两个日期的大小
     *
     * @param time1 yyyy-MM-dd HH:mm:ss
     * @param time2 yyyy-MM-dd HH:mm:ss
     * @return true time1>time2
     */
    public static boolean comparDate(String time1, String time2) {
        DateFormat df = new SimpleDateFormat(PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE);
        boolean result = false;
        try {
            Date dt1 = df.parse(time1);
            Date dt2 = df.parse(time2);
            if (dt1.getTime() >= dt2.getTime()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Calendar convertDate(String d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (TextUtils.isEmpty(d)) {
            return calendar;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YEAR_MONTH_DAY);

        Date date;
        try {
            date = sdf.parse(d);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    /**
     * 后台其他子系统返回的时间默认格式
     */
    public static final String webSysPattern = "MMM d, yyyy K:m:s a";

    /**
     * "MMM d, yyyy K:m:s a"
     * 格式化后台传递的时间 后台不是POS
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatSysTime(String date, String pattern) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(webSysPattern, Locale.US);
        String time = date;
        try {
            Date d2 = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat(pattern, Locale.getDefault());
            time = sdf2.format(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获取本周开始时间
     *
     * @param date
     * @return
     */
    public static Date getWeekFirstDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayFirstTime(cal.getTime());
    }

    /**
     * 获取本周结束时间
     *
     * @param date
     * @return
     */
    public static Date getWeekLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekFirstDate(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayLastTime(weekEndSta);
    }

    public static int getAgeByBirth(Date birthDay) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return age;
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 根据 cutDate 计算时间.
     *
     * @param date    需要计算的时间
     * @param cutDate 时间差
     * @return 返回时间 时分秒都是0
     */
    public static Date getbeforeOrAfterDate(@NonNull Date date, int cutDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, cutDate);
        return getDayFirstTime(cal.getTime());
    }

    public static Date getbeforeOrAfterDate(@NonNull Date date,int field, int cutDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, cutDate);
        return getDayFirstTime(cal.getTime());
    }

    public static Date getUpperMonthFirstDate(@NonNull Date date){
        return getMonthFirstDate(getCutMonthFirstDate(date,-1));
    }

    public static Date getUpperMonthLastDate(@NonNull Date date){
        return getMonthLastDate(getCutMonthLastDate(date,-1));
    }

    public static Date getCutMonthFirstDate(@NonNull Date date,int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return getMonthFirstDate(cal.getTime());
    }

    public static Date getCutMonthLastDate(@NonNull Date date,int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,amount);
        return getMonthLastDate(cal.getTime());
    }

    /**
     * 获取当年的开始时间
     * @param date
     * @return
     */
    public static Date getYearStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当年的最后时间
     *
     * @param date 时间
     * @return
     */
    public static Date getYearEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 判断日期是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1,Date date2){
        if (date1 == date2)return true;
        if (date1 == null || date2 == null)
            return false;
        return TextUtils.equals(DateFormatUtils.format(date1,DateFormatUtils.PATTERN_DATE),DateFormatUtils.format(date2,DateFormatUtils.PATTERN_DATE));
    }

    /**
     * 判断日期是否大于现在
     * @param dateStr 比较的日期
     * @return true 早于现在  false 晚于现在
     */
    public static boolean afterToday(String dateStr){
        Date date = parse(dateStr,PATTERN_YEAR_MONTH_DAY_HOUR_MINUTE);
        if (date == null)
            return false;
        return date.before(new Date());
    }

    /**
     * 格式化日期显示
     * @param startTime 开始时间 例:"2020-05-20 10:00:35"
     * @param endTime 结束时间   例:"2020-05-20 10:30:35"
     * @return 1.开始时间和结束时间相同返回--->2020-05-20(周三) 10:07
     *         2.开始时间和结束时间不同返回--->2020-05-20(周三) 10:00-10:30
     */
    public static String formatIntervalTime(String startTime,String endTime){
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime))
            return TextUtils.isEmpty(startTime) ? TextUtils.isEmpty(endTime)? "" : endTime : startTime;
        Date date = parse(startTime,TIME_PATTERN);
        if (date == null)
            return "";
        if (TextUtils.equals(startTime, endTime)) {
            return format(date,DATE_FMT_3)+"("+getWeek(date)+") "+format(date,"HH:mm");
        }
        return format(date,DATE_FMT_3)+"("+getWeek(date)+") "+format(date,"HH:mm")+" ~"+endTime.substring(endTime.length() - 9,endTime.length() - 3);
    }


    public static String formatDate(String time){
        if (TextUtils.isEmpty(time))
            return "";
        if(time.length() < 10)
            return time;
        return time.substring(0,10);
    }
}
