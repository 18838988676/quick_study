package com.cn.alarm;


/**
 * @Copyright：botBrain.ai
 * @author: WangMingChao
 * @Date: 2019/11/5.
 * @Description:数据去重
 **/
public interface IntervalAlarmService {

    /**
     *  判断数据是否重复
     * @param id  模板id
     * @param toUser 接收者
     * @return true:不重复
     */
    boolean isDataHaveResult(String id, String toUser);

    /**
     *  保存数据，如果数据不存在则保存数据并返回true，否则返回false
     * @param id  模板id
     * @param toUser 接收者
     * @param time 间隔时间。按照分钟计算，不存在的话，默认2分钟；
     * @return true:不重复
     */
    boolean saveAndGetResult(String id, String toUser, int time);

}
