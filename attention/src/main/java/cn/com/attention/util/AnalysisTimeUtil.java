package cn.com.attention.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright：botBrain.ai
 * Author: WangYingChun
 * Date: 2019/7/31.
 * Description:
 */
public class AnalysisTimeUtil {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 获取本月的第一天和最后一天
     * @param date
     * @return
     */
    public static Map<String, String> getFirstdayAndLastdayMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        Date theDate = calendar.getTime();
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);
        //加一个月
        calendar.set(Calendar.DATE, 1);
        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);
        //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 获取当前月往前num月日期
     *
     * @param num
     * @return
     */
    public static Date getDateBeforeNumMonth(Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -num);
        return calendar.getTime();
    }

    /**
     * 获取两个日期相隔周数
     * @param firstDay
     * @param lastDay
     * @return
     */
    public static Long getWeekStep(Integer firstDay, Integer lastDay) throws ParseException {
        return Long.valueOf(getDayStep(firstDay, lastDay) / 7) + 1L;
    }


    /**
     * 获取两天之间的间隔
     * @param firstday
     * @param lastDay
     * @return
     */
    public static Long getDayStep(Integer firstday, Integer lastDay) throws ParseException {
        Date first = dateFormat.parse(String.valueOf(firstday));
        Date last = dateFormat.parse(String.valueOf(lastDay));
        return (first.getTime() - last.getTime()) / (1000 * 60 * 60 * 24L) + 1L;
    }



    /**
     * 获取两日期之间相隔月数
     * @param firstDay
     * @param lastDay
     * @return
     */
    public static Long getMonthStep(Integer firstDay, Integer lastDay) throws ParseException {
        Date d1 = dateFormat.parse(String.valueOf(firstDay));
        Date d2 = dateFormat.parse(String.valueOf(lastDay));
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int result = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        int month = (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12;
        return Long.valueOf(result + month);
    }

}
