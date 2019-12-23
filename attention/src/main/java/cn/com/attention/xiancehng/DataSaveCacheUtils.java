package cn.com.attention.xiancehng;

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
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/11.
 * @Description: 中国移动接口数据缓存
 */
@SpringBootConfiguration
@Slf4j
public class DataSaveCacheUtils {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private DataDBGet dataCacheGet;

    public static ScheduledExecutorService getScheduledExecutorService(String threadPoolName) {
        return new ScheduledThreadPoolExecutor(20,new ThreadFactoryBuilder().setNameFormat(threadPoolName+"-%d").build());
    }
    public static ScheduledExecutorService scheduledExecutorServiceCheck() {
        return new ScheduledThreadPoolExecutor(20);
    }

    @PostConstruct
    private void initDataCachingJob() {
        log.info("start four cacheing");
        Instant inst = Instant.now();
        getScheduledExecutorService("dataUtils4").scheduleAtFixedRate(() -> {dataUtils4();}, 3, 3, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils4").scheduleAtFixedRate(() -> {dataUtils();}, 3, 3, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils4").scheduleAtFixedRate(() -> {dataUtils3();}, 3, 3, TimeUnit.SECONDS);
        getScheduledExecutorService("dataUtils4").scheduleAtFixedRate(() -> {dataUtils2();}, 3, 3, TimeUnit.SECONDS);
        Instant inst2 = Instant.now();
        log.info("cast all time:" + Duration.between(inst, inst2).toMinutes()+"分钟");
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
        Future<Map<String, List<UserProfileKeyword>>> future = scheduledExecutorServiceCheck().submit(() -> dataCacheGet.dataCach1());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){
            log.info("获取用户关键词统计 null");
            return  "获取用户关键词统计 null";}
        try {
            for (Future<Map<String, List<UserProfileKeyword>>> fu : futureList) {
                result = fu.get();
            }
        } catch (Exception e) {
            log.error("获取用户关键词统计  ", e);
            return null;
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
        log.info("获取用户关键词统计 success");
        return "获取用户关键词统计 success";

    }

    /**
     * 获取用户在线时长统计
     *
     * @return
     */
    private String dataUtils2() {

        System.out.println("-------------------->>>>"+Thread.currentThread().getThreadGroup().getName());
        System.out.println("--------------------->>>"+Thread.currentThread().getName());

        log.info("获取用户在线时长统计start");
        List<Future<Map<String, List<DateTs>>>> futureList = new ArrayList<>();
        Map<String, List<DateTs>> result = null;
        Future<Map<String, List<DateTs>>> future = scheduledExecutorServiceCheck().submit(() -> dataCacheGet.dataCach2());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){
            log.info("获取用户关键词统计 null");
            return "获取用户关键词统计 null";}
        try {
                for (Future<Map<String, List<DateTs>>> fu : futureList) {
                    result = fu.get();
            }
        } catch (Exception e) {
            log.error("获取用户关键词统计:", e);
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
        log.info("获取用户在线时长统计 success");
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
        Future<Map<String, List<PostKnowledgeTs>>> future = scheduledExecutorServiceCheck().submit(() -> dataCacheGet.dataCach4());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){
            log.info("获取用户岗位学习时长 null");
            return  "获取用户岗位学习时长 null";}
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
        log.info("获取用户岗位学习时长 success");
        return "获取用户岗位学习时长 success";
    }

    /**
     * 获取用户基本信息
     *
     * @return
     */
    private String dataUtils4() {
        log.info("获取用户基本信息start");
        List<Future<Map<String, UserProfileStatistics>>> futureList = new ArrayList<>();
        Map<String, UserProfileStatistics> result = null;
        Future<Map<String, UserProfileStatistics>> future = scheduledExecutorServiceCheck().submit(() -> dataCacheGet.dataCach3());
        futureList.add(future);
        if(CollectionUtils.isEmpty(futureList)){
            log.info("获取用户基本信息为null");
            return "获取用户基本信息为null";}
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
        log.info("获取用户基本信息 success345445345345345");
        return "获取用户基本信息 succes123s";
    }


}
