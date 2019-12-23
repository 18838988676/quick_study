package cn.com.attention.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright：botBrain.ai
 * Author: SongXiaoGuang
 * Date: 2018/1/25.
 * Description:
 */


public class TimeUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH时mm分");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy年MM月dd日");

    public static String timestampToCurrentStr(long timestamp) {
        long sec = 1000;
        long min = 60 * sec;
        long hour = 60 * min;
        long day = 24 * hour;
        long month = 30 * day;
        long year = 12 * month;
        long t = System.currentTimeMillis() - timestamp;
        if (t <= 0) {
            return null;
        }
        if (t < min) {
            return "刚刚";
        } else if (t >= min && t < hour) {
            return new Double(Math.floor(t / min)).intValue() + "分钟前";
        } else if (t >= hour && t < day) {
            return new Double(Math.floor(t / hour)).intValue() + "小时前";
        } else if (t >= day && t < 2 * day) {
            return "昨天 " + sdf2.format(timestamp);
        } else if (t >= 2 * day && t < 3 * day) {
            return "前天 " + sdf2.format(timestamp);
        } else if (t >= 3 * day && t < 10 * day) {
            return new Double(Math.floor(t / day)).intValue() + "天前";
        } else {
            return sdf3.format(timestamp);
        }
    }

    //获取星期几
    private static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    //本周一
    public static Date getThisWeekMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getThisMonthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getThisYearDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    //格式化 yyyyMMdd
    public static String formatYyyyMMdd(Date date) {
        return sdf.format(date);
    }

    public static String formatYyyyMMdd(long timestamp) {
        return sdf.format(timestamp);
    }

    //格式化 yyyy年MM月dd日 HH:mm
    public static String formatYyyyMMddHHmm(Date date) {
        return sdf4.format(date);
    }

    //格式化 yyyy年MM月dd日 HH:mm
    public static String formatYyyyYearMmMonthddDay(long timestamp) {
        return sdf8.format(timestamp);
    }

    //格式化 yyyy年MM月dd日 HH:mm
    public static String formatYyyyMMddHHmm(long timestamp) {
        return sdf4.format(timestamp);
    }

    public static String formatYyyy(long timestamp) {
        return sdf4.format(timestamp);
    }


    // 格式化 yyyy-MM-dd HH:mm
    public static String formatDate(long timestamp) {
        return sdf6.format(timestamp);
    }

    // 格式化 yyyy-MM-dd HH:mm:ss
    public static String formatYyyyMMddHHmmss(long timestamp) {
        return sdf7.format(timestamp);
    }

    //格式化 yyyy-MM-dd
    public static String formatYyyyMMddSplitMid(Date date) {
        return sdf5.format(date);
    }


    /**
     * 获取n天前 YYYYMMDD ::int 值
     *
     * @param n 天数
     * @return int date
     * @throws ParseException
     */
    public static Integer getOnedayBefore(int n) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.DATE, -n);
        return Integer.parseInt(sdf.format(cal.getTime()));
    }

    public static Integer getCurrentDailyNum(long timestamp) {
        return Integer.parseInt(sdf.format(timestamp));
    }

    public static Integer getLastWeek(String daily, int n) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(daily));
            cal.add(Calendar.DATE, -n);
            return Integer.parseInt(sdf.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //格式化 MM.dd
    public static String formatMMddSplitComma(int daily) {
        String str = String.valueOf(daily);
        return str.substring(4, 6) + "." + str.substring(6, 8);
    }

    // 秒转分钟，小时
    public static String convert2HourMinute(int second) {
        int hour = second / 3600;
        if (hour == 0) {
            int minute = second / 60;
            return (minute == 0 ? 1 : minute) + "分钟";
        } else {
            int minute = (second - hour * 3600) / 60;
            return hour + "小时" + (minute == 0 ? "" : minute + "分钟");
        }

    }

    //获取时长分钟
    public static String getDuration(long second) {
        return new Double(Math.ceil(second / 60F)).intValue() + "分钟";
    }

    //获取时长分钟
    public static Long second2Min(long second) {
        return ((long) Math.ceil(second / 60F));
    }

    public static Long second2Min(Double second) {
        return ((long) Math.ceil(second / 60F));
    }

    public static String getDurationDetail(long second) {
        if (second <= 0) {
            return "5秒以内";
        } else if (second > 0 && second < 60) {
            return second + "秒";
        } else if (second == 60) {
            return "1分钟";
        } else {
            return second / 60 + "分" + second % 60 + "秒";
        }
    }


    //天,周,月,季度,年
    public static String convertCommonToFormatDate(String daily, Integer formatType) {
        String year = "";
        String month = "";
        String day = "";
        if (daily == null || daily.length() != 8) {
            return null;
        } else {
            year = daily.substring(0, 4);
            month = daily.substring(4, 6);
            day = daily.substring(6, 8);
        }
        switch (formatType) {
            case 1:
                return year + "." + month + "." + day;
            case 2:
                return year + "." + month + "." + "第" + (Integer.valueOf(day) / 7 + 1) + "周";
            case 3:
                return year + "." + month + "月";
            case 4:
                return year + ".第" + ((Integer.valueOf(month) - 1) / 3 + 1) + "季度";
            case 5:
                return year;
            default:
                return daily;
        }

    }

    public static Long convertyyyyMMdd2timestamp(String daily) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = simpleDateFormat.parse(daily);
            return date.getTime();
        } catch (Exception e) {
            return 0L;
        }

    }

    public static String convertCommonToFormatDate(Integer daily, Integer formatType) {
        return convertCommonToFormatDate(String.valueOf(daily), formatType);
    }

    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dayAgoOneDayStr(String str) {
        return dayAfterNdays(str, 1);
    }

    public static String dayAfterNdays(String str, Integer n) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(strToDate(str));
        ca.set(Calendar.DATE, ca.get(Calendar.DATE) + n);
        return new SimpleDateFormat("yyyyMMdd").format(ca.getTime());
    }

    public static String dayAgoOneDayStr(Integer str) {
        return dayAgoOneDayStr(String.valueOf(str));
    }

    public static void main(String[] args) {
        System.out.println(getThisYearDay());
    }
}