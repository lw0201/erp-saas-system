package com.zhuoxun.it.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * 时间处理工具类
 * 
 *
 */
public class DateUtils {
    public static final String TIME_FORMAT = "HH:mm";

    /**
     * 按照参数format的格式，日期转字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 时间转换为yyyy/MM/dd HH:mm:ss格式的字符串
     * 
     * @param date
     * @return
     */
    public static String dateToString2(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }

    /**
     * 时间转换为yyyyMMddHHmmss格式的字符串
     * 
     * @param date
     * @return
     */
    public static String dateToString3(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 时间转换为yyyy-MM-dd格式的字符串
     * 
     * @param date
     * @return
     */
    public static String simple(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 时间转换为yyyy/MM/dd格式的字符串
     * 
     * @param date
     * @return
     */
    public static String simple2(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    /**
     * 时间转换为yyyyMMdd格式的字符串
     * 
     * @param date
     * @return
     */
    public static String simple3(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 字符串转换为时间类型
     * 
     * @param dateString
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date strToDate(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转换为时间类型
     * 
     * @param dateString
     *            yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static Date strToDate2(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转换为时间类型
     * 
     * @param dateString
     *            yyyy-MM-dd
     * @return
     */
    public static Date strToYYMMDDDate(String dateString) {
        if (null == dateString)
            return new Date();
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 年-月 字符串转时间
     * 
     * @param dateString
     *            yyyy-MM
     * @return
     */
    public static Date strToYYMMDate(String dateString) {
        if (null == dateString)
            return new Date();
        try {
            return new SimpleDateFormat("yyyy-MM").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 年-月 日期转字符串
     * 
     * @param dateString
     *            yyyy-MM
     * @return
     */
    public static String dateTostr(Date date) {
        if (null == date)
            date = new Date();
        return new SimpleDateFormat("yyyy-MM").format(date);
    }

    /**
     * 字符串转换为时间类型
     * 
     * @param dateString
     *            yyyy/MM/dd
     * @return
     */
    public static Date strToYYMMDDDate2(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转换为时间类型
     * 
     * @param dateString
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date strToDate3(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统时间并返回时间格式 yyyy-MM-dd HH:mm:ss
     * 
     * @return Date
     */
    public static Date getDate() {
        DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            String currentDates = YYYY_MM_DD_MM_HH_SS.format(new Date());

            return YYYY_MM_DD_MM_HH_SS.parse(currentDates);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统时间并返回时间格式 yyyy-MM-dd HH:mm:ss
     * 
     * @return Date
     */
    public static String currentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取系统时间并返回时间格式 yyyy-MM-dd
     * 
     * @return Date
     */
    public static String currentDate2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取系统时间并返回时间格式 yyyyMMdd
     * 
     * @return Date
     */
    public static String getDate2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    /**
     * 换算时间
     * 
     * @param time
     *            HH:mm 格式
     * @return Long
     */
    public static Long compareTo(String time) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取时间格式字符串
     * 
     * @param date
     * @return HH:mm 格式
     */
    public static String formatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        try {
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
        } catch (ParseException e) {
            e.printStackTrace(); // 用日志代替此处
        }
        try {
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace(); // 用日志代替此处
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个时间之间相差的天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {
        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        days = (end - start) / 86400000;
        return days;
    }

    /**
     * 计算两个时间之间相差的分钟数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffMinutes(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 60000;
    }

    /**
     * 计算两个时间之间相差的秒数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffSecond(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 1000;
    }

    /**
     * 日期加上月数的时间
     * 
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     * 
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     * 
     * @param date
     * @param year
     * @return
     */
    public static Date dateAddYear(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     * 
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        if (endDate == null) {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1) {
            result.append("过期");
        } else {
            long temp = 1000 * 60 * 60 * 24;
            // 天数
            long d = times / temp;

            // 小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            // 分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    /**
     * 当前时间减去分钟数得到的时间
     * 
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static String getDateMinusMinutes(int minutes) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        String datetest = formatter.format(nowTime);
        Date date = formatter.parse(datetest);
        long Time1 = (date.getTime() / 1000) - 60 * minutes;// 减去30分
        date.setTime(Time1 * 1000);
        return formatter.format(date);
    }

    /**
     * 和当前时间比较是否在给定的时长内
     * 
     * @param validTime
     *            给定的时间
     * @param time
     *            给定的时长（s）
     * @return true 有效 false 无效
     */
    public static boolean inValidTime(Date validTime, int time) {
        long currTime = System.currentTimeMillis();
        long valid = validTime.getTime();

        return ((currTime - valid) < time * 1000);
    }

    /**
     * 验证当前时间是否在开始结束时间内
     * 
     * @param currentTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证时间是否在结束时间之前
     * 
     * @param nowTime
     * @param endTime
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date endTime) {
        if (nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取年
     * 
     * @param time
     * @return
     */
    public static int getYear(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * 
     * @param time
     * @return
     */
    public static int getMonth(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * 
     * @param time
     * @return
     */
    public static int getDay(Date time) {
        if (time == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取年
     * 
     * @return
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * 
     * @return
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * 
     * @return
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * 
     * @param startDateStr
     * @return
     */
    public static Date strDateToStartDate(String startDateStr) {

        return DateUtils.strToDate(startDateStr + " 00:00:00");
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * 
     * @param startDateStr
     * @return
     */
    public static Date strDateToEndDate(String endDateStr) {

        return DateUtils.strToDate(endDateStr + " 23:59:59");
    }

    /**
     * 当前时间减去天数得到的时间
     * 
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Date getDateMinusDay(int day) {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    /**
     * 指定减去天数得到的时间
     * 
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Date getDateMinusDay2(Date time, int day) {
        // Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    /**
     * 是否日期yyyy-mm-dd(yyyy/mm/dd)
     * 
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        if (null == date) {
            return false;
        }
        return date.matches("^([1-2]\\d{3})[\\/|\\-](0?[1-9]|10|11|12)[\\/|\\-]([1-2]?[0-9]|0[1-9]|30|31)$");
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 根据生日获取年龄
     * 
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if (birthday == null) {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        Calendar currentDate = Calendar.getInstance();
        return currentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }

    /**
     * 获取上周时间
     * 
     * @return
     */
    public static String getFirstWeekDate() {
        Calendar cal = Calendar.getInstance();
        // n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = -1;
        String monday;
        cal.add(Calendar.DATE, n * 7);
        // 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday;
    }

    /**
     * 获取上周天
     * 
     * @return
     */
    public static String getSundayWeekDate() {
        Calendar cal = Calendar.getInstance();
        // n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = 0;
        String monday;
        cal.add(Calendar.DATE, n * 7);
        // 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday;
    }

    /**
     * 获取当前时间戳
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Timestamp getTimeCal() throws ParseException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        return t;
    }

    /**
     * 获取当前时间戳+30
     * 
     * @param date
     * @return
     */
    public static Timestamp getTimeCalTh(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2);
        Timestamp t = new Timestamp(calendar.getTimeInMillis());
        return t;
    }

    /**
     * 获取指定天数之前或之后的 日期时间 如 customDays = -7, 则是获取7天前的日期时间
     * 
     * @param customDays
     *            指定的天数
     * @param isZero为true获取零点时间
     *            如 2001-01-01 00:00:00 false获取该日结束时间 如2001-01-01 23:59:59
     * @return
     */
    public static String getCustomDate(int customDays, boolean isZero) {
        Calendar cal = Calendar.getInstance();
        String zero = " 00:00:00";
        String end = " 23:59:59";
        String result = null;
        try {
            cal.add(Calendar.DATE, customDays);
            if (isZero) {
                result = simple(cal.getTime()) + zero;
            } else if (!isZero) {
                result = simple(cal.getTime()) + end;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当天开始时间
     * 
     * @return
     */
    public static Date getStartDate() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取指定日期的开始时间
     * 
     * @param date
     *            指定的日期
     * @return
     */
    public static Date getStartDate(Date date) {
        Calendar todayStart = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天结束时间
     * 
     * @return
     */
    public static Date getEndDate() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取本年度开始时间
     * 
     * @return xxxx-01-01 00:00:00
     */
    public static java.util.Date getBeginDayOfYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, gc.get(1));
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return strDateToStartDate(simple(cal.getTime()));
    }

    /**
     * 获取本年度结束时间
     * 
     * @return xxxx-12-31 23:59:59
     */
    public static java.util.Date getEndDayOfYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, gc.get(1));
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return strDateToEndDate(simple(cal.getTime()));
    }

    /**
     * 获取本周的开始时间 ,默认从星期一00:00:00开始
     * 
     * @return
     */
    public static String getThisWeekStartDate() {
        Calendar cal = Calendar.getInstance();
        String zero = " 00:00:00";
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return simple(cal.getTime()) + zero;
    }

    /**
     * 获取本月1号开始的时间
     * 
     * @return 如 2001-01-01 00:00:00
     */
    public static String getThisMonthStartDate() {
        Calendar cal = Calendar.getInstance();
        String zero = " 00:00:00";
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return simple(cal.getTime()) + zero;
    }

    /**
     * 获取指定日期的月份最后一天
     * 
     * @param date
     * @return Date
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        // 将分钟至59
        c.set(Calendar.MINUTE, 59);
        // 将秒至59
        c.set(Calendar.SECOND, 59);
        // 将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTime();
    }

    /**
     * 获取过去 任意天内的日期数组
     * 
     * @param intervals
     *            多少天内
     * @return 日期数组
     */
    public static ArrayList<String> getPastDate(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<String>();
        for (int i = 0; i < intervals; i++) {
            pastDaysList.add(pastDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     * 
     * @param past
     * @return
     */
    public static String pastDate(int past) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -past);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);
        return result;
    }

    public static String pastDate(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -past);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     * 
     * @param past
     * @return
     */
    public static String fetureDate(int past) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, past);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);
        return result;
    }

    public static String fetureDate(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, past);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);
        return result;
    }

    /**
     * @param date1
     *            <String>
     * @param date2
     *            <String>
     * @return int
     * @throws ParseException
     */
    public static int getDateSpace(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if (c2.getTimeInMillis() < c1.getTimeInMillis())
            return -1;

        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-9-30 d2 = 2015-12-16
        int yearInterval = year2 - year1;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month2 < month1 || month1 == month2 && day2 < day1)
            yearInterval--;
        // 获取月数差值
        int monthInterval = (month2 + 12) - month1;
        if (day2 > day1)
            monthInterval++;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    /**
     * 计算得到MongoDB存储的日期，（默认情况下mongo中存储的是标准的时间，中国时间是东八区，存在mongo中少8小时，所以增加8小时）
     * 
     * @param date
     * @return
     */
    public static Date getMongoDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 8);
        return strToDate(sdf.format(ca.getTime()));
    }

    /**
     * 日期格式字符串转换成时间戳
     * 
     * @param date
     *            字符串日期
     * @param format
     *            如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
