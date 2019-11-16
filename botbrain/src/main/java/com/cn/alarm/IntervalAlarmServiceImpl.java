package com.cn.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright：botBrain.ai
 * @author: WangMingChao
 * @Date: 2019/11/5.
 * @Description:数据去重
 **/
@Configuration("intervalAlarmService898978151510")
//@Configuration  注入时写：IntervalAlarmService IntervalAlarmServiceImpl;或IntervalAlarmService intervalAlarmServiceImpl;
public class IntervalAlarmServiceImpl implements IntervalAlarmService {

    private ConcurrentHashMap<String, Long> concurrentHashMap;
    @Autowired
    private ScheduledExecutorService scheduledExecutorServiceAlarmAtPackageAlarm56548787;

    public IntervalAlarmServiceImpl() {
    }

    @PostConstruct
    public void init(){
        concurrentHashMap = new ConcurrentHashMap<String, Long>();
        scheduledExecutorServiceAlarmAtPackageAlarm56548787.scheduleWithFixedDelay(this::removeOutOfData,2,5,TimeUnit.SECONDS);
    }


    /**
     * 剔除已经过期的key
     */
    private void removeOutOfData() {
        long nowTime= System.currentTimeMillis();
        if (!CollectionUtils.isEmpty(concurrentHashMap)) {
            for (Map.Entry<String, Long> entry : concurrentHashMap.entrySet()) {
                //如果map中的时间在当前时间之前，表示已过期，删除key
                if (isBefore(entry.getValue(),nowTime)) {
                    concurrentHashMap.remove(entry.getKey());
                }
            }
        }
    }

    /**
     * 判断数据是否存在
     *
     * @param id     模板id
     * @param toUser 接收者
     * @return true:存在
     */
    @Override
    public boolean isDataHaveResult(String id, String toUser) {
        return concurrentHashMap.containsKey(toUser + id);
    }

    /**
     * 保存数据，如果数据不存在则保存数据并返回true，否则返回false
     *
     * @param id     模板id
     * @param toUser 接收者
     * @param time   间隔时间
     * @return true:不重复
     */
    @Override
    public boolean saveAndGetResult(String id, String toUser, int time) {
        if (!isDataHaveResult(id, toUser)) {
            concurrentHashMap.put(toUser + id, getDate(time));
            return true;
        }
        return false;
    }

    /**
     * 获得指定分钟数后的时间
     *
     * @param amount
     * @return
     */
    private Long getDate(int amount) {
        return System.currentTimeMillis() + amount * 1000 * 60L;
    }

    /**
     * 判断时间先后
     *
     * @param time
     * @return
     */
    private boolean isBefore(long time,long nowTime) {
        return time < nowTime;
    }

}
