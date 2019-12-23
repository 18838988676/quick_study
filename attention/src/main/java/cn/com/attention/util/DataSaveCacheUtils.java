package cn.com.attention.util;

import com.alibaba.fastjson.JSONObject;
import com.botbrain.usage.entity.DateTs;
import com.botbrain.usage.entity.PostKnowledgeTs;
import com.botbrain.usage.entity.statistic.UserProfileKeyword;
import com.botbrain.usage.entity.statistic.UserProfileStatistics;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/11.
 * @Description: 中国移动接口数据缓存
 */
@Configuration
@Slf4j
public class DataSaveCacheUtils {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private DataDBGet dataCacheGet;
//    private  volatile int n=0;
private int n=0;
    private  ScheduledExecutorService scheduledExecutorServiceCheck = new ScheduledThreadPoolExecutor(20, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {

            return new Thread(r, "----usage data caching thread ：" + n++);
        }
    });



    public static ScheduledExecutorService getScheduledExecutorService(String threadPoolName) {
        return new ScheduledThreadPoolExecutor(20,new ThreadFactoryBuilder().setNameFormat(threadPoolName+"-%d").build());
    }

    @PostConstruct
    private void initDataCachingJob() {
        getScheduledExecutorService("dataUtils4").scheduleAtFixedRate(() -> {dataUtils4();}, 0, 20, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils").scheduleAtFixedRate(() -> {dataUtils();}, 0, 20, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils3").scheduleAtFixedRate(() -> {dataUtils3();}, 0, 20, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils2").scheduleAtFixedRate(() -> {dataUtils2();}, 0, 20, TimeUnit.SECONDS);
//        scheduledExecutorServiceCheck.scheduleAtFixedRate(() -> {dataUtils4();}, 0, 20, TimeUnit.SECONDS);
//        scheduledExecutorServiceCheck.scheduleAtFixedRate(() -> {dataUtils();}, 0, 20, TimeUnit.SECONDS);
//        scheduledExecutorServiceCheck.scheduleAtFixedRate(() -> {dataUtils2();}, 0, 1, TimeUnit.MINUTES);
//        scheduledExecutorServiceCheck.scheduleAtFixedRate(() -> {dataUtils3();}, 0, 1, TimeUnit.MINUTES);
        log.info("init statr ....success...");
//        scheduledExecutorServiceCheck.scheduleAtFixedRate(() -> {data();}, 1, 2, TimeUnit.MINUTES);
    }

    //执行4个缓存
    private void data() {
        log.info("start four cacheing");
        Instant inst = Instant.now();
        String msg=null;
        // 获取用户基本信息
        msg=  dataUtils4();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            log.error("-->sleep ", e.getMessage());
        }
        log.info("获取用户基本信息,{}",msg);
        // 获取用户关键词统计
        msg=  dataUtils();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            log.error("-->sleep ", e.getMessage());
        }
        log.info("获取用户关键词统计",msg);

//        获取用户在线时长统计
        msg=   dataUtils2();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            log.error("-->sleep ", e.getMessage());
        }
        log.info("获取用户在线时长统计",msg);


        //        获取用户岗位学习时长
       msg= dataUtils3();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            log.error("-->sleep ", e.getMessage());
        }
        log.info("获取用户岗位学习时长,{}",msg);
        Instant inst2 = Instant.now();
        System.out.println("总时间：" + Duration.between(inst, inst2).toMinutes()+"分钟");
        log.info("end four cacheing");
    }


    /**
     * 获取用户关键词统计
     *
     * @return
     */
    private String dataUtils() {
        log.info("获取用户关键词统计start");
        List<Future<Map<String, List<UserProfileKeyword>>>> futureList = new ArrayList<>();
        Map<String, List<UserProfileKeyword>> result = null;
        Future<Map<String, List<UserProfileKeyword>>> future = scheduledExecutorServiceCheck.submit(() -> dataCacheGet.dataCach1());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){return  "获取用户关键词统计 null";}
        try {
            for (Future<Map<String, List<UserProfileKeyword>>> fu : futureList) {
                result = fu.get();
            }
        } catch (Exception e) {
            log.error("获取用户关键词统计  ", e);
        }
        if (!result.isEmpty()) {
        for (Map.Entry<String, List<UserProfileKeyword>> mapResult : result.entrySet()) {
            if (CollectionUtils.isNotEmpty(mapResult.getValue())) {
                List<String> list = mapResult.getValue().stream().map(i -> JSONObject.toJSONString(i)).collect(Collectors.toList());
                redisTemplate.delete(mapResult.getKey());
                redisTemplate.opsForList().leftPushAll(mapResult.getKey(), list);
                redisTemplate.expire(mapResult.getKey(), 5, TimeUnit.MINUTES);
            }
        }}
        return "获取用户关键词统计 success";
    }

    /**
     * 获取用户在线时长统计
     *
     * @return
     */
    private String dataUtils2() {
        log.info("start 获取用户在线时长统计");
        List<Future<Map<String, List<DateTs>>>> futureList = new ArrayList<>();
        Map<String, List<DateTs>> result = null;
        Future<Map<String, List<DateTs>>> future = scheduledExecutorServiceCheck.submit(() -> dataCacheGet.dataCach2());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){return "获取用户关键词统计 null";}
        try {
                for (Future<Map<String, List<DateTs>>> fu : futureList) {
                    result = fu.get();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        if (!result.isEmpty()) {
            for (Map.Entry<String, List<DateTs>> mapResult : result.entrySet()) {
                if (CollectionUtils.isNotEmpty(mapResult.getValue())) {
                    List<String> list = mapResult.getValue().stream().map(i -> JSONObject.toJSONString(i)).collect(Collectors.toList());
                    redisTemplate.delete(mapResult.getKey());
                    redisTemplate.opsForList().leftPushAll(mapResult.getKey(), list);
                    redisTemplate.expire(mapResult.getKey(), 5, TimeUnit.MINUTES);
                }
            }
        }
        return "获取用户在线时长统计 success";
    }

    /**
     * 获取用户岗位学习时长
     *
     * @return
     */
    private String dataUtils3() {
        log.info("获取用户岗位学习时长stary");
        List<Future<Map<String, List<PostKnowledgeTs>>>> futureList = new ArrayList<>();
        Map<String, List<PostKnowledgeTs>> result = null;
        Future<Map<String, List<PostKnowledgeTs>>> future = scheduledExecutorServiceCheck.submit(() -> dataCacheGet.dataCach4());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){return  "获取用户岗位学习时长 null";}
        try {
            for (Future<Map<String, List<PostKnowledgeTs>>> fu : futureList) {
                result = fu.get();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        if (!result.isEmpty()) {
        for (Map.Entry<String, List<PostKnowledgeTs>> mapResult : result.entrySet()) {
            if (CollectionUtils.isNotEmpty(mapResult.getValue())) {
                List<String> list = mapResult.getValue().stream().map(i -> JSONObject.toJSONString(i)).collect(Collectors.toList());
                redisTemplate.delete(mapResult.getKey());
                redisTemplate.opsForList().leftPushAll(mapResult.getKey(), list);
                redisTemplate.expire(mapResult.getKey(), 5, TimeUnit.MINUTES);
            }
        }}
        return "获取用户岗位学习时长 success";
    }

    /**
     * 获取用户基本信息
     *
     * @return
     */
    private String dataUtils4() {
        try {
            TimeUnit.MINUTES.sleep(50);

        }catch (Exception e){}
        log.info("start 获取用户基本信息");
        List<Future<Map<String, UserProfileStatistics>>> futureList = new ArrayList<>();
        Map<String, UserProfileStatistics> result = null;
        Future<Map<String, UserProfileStatistics>> future = scheduledExecutorServiceCheck.submit(() -> dataCacheGet.dataCach3());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){return "获取用户基本信息为null";}
        try {
            for (Future<Map<String, UserProfileStatistics>> fu : futureList) {
                result = fu.get();
            }
        } catch (Exception e) {
            log.error("error 获取用户基本信息 ", e);
        }
        if (!result.isEmpty()) {
        for (Map.Entry<String, UserProfileStatistics> mapResult : result.entrySet()) {
            if (mapResult.getValue() != null) {
                redisTemplate.delete(mapResult.getKey());
                redisTemplate.opsForValue().set(mapResult.getKey(), new Gson().toJson(mapResult.getValue()));
                redisTemplate.expire(mapResult.getKey(), 5, TimeUnit.MINUTES);
            }
        }}
        return "获取用户基本信息 success";
    }


}
