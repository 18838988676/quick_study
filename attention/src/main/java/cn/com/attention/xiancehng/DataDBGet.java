package cn.com.attention.xiancehng;

import com.botbrain.usage.dao.DataCacheDao;
import com.botbrain.usage.entity.DateTs;
import com.botbrain.usage.entity.PostKnowledgeTs;
import com.botbrain.usage.entity.statistic.StatisticQueryParameter;
import com.botbrain.usage.entity.statistic.UserProfileKeyword;
import com.botbrain.usage.entity.statistic.UserProfileStatistics;
import com.botbrain.usage.service.impl.UserStaticsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/12.
 * @Description: 获取缓存的数据
 */
@Configuration
@Slf4j
public class DataDBGet {

    @Autowired
    private DataCacheDao dataCacheDao;
    @Autowired
    UserStaticsServiceImpl userStaticsService;

    /**
     * 获取用户关键词统计
     * @return
     */
    protected Map<String, List<UserProfileKeyword>> dataCach1() {
        Map<String, List<String>> map = getOsAndUid();
        List<UserProfileKeyword> data = new ArrayList<>(32);
        Map<String, List<UserProfileKeyword>> result = new HashMap<>(32);
        StatisticQueryParameter statisticQueryParameter = new StatisticQueryParameter();
        statisticQueryParameter.setBegin_daily(getOnedayafter1(0));
        statisticQueryParameter.setEnd_daily(getOnedayafter1(1));
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String uid : entry.getValue()) {
                statisticQueryParameter.setUid(uid);
                statisticQueryParameter.setOs_key(entry.getKey());
                data = userStaticsService.getOneUidKeyword(statisticQueryParameter);
                result.put(entry.getKey() + ":" + uid+":user_keyword", data);
            }
        }
        return result;
    }

    /**
     * 获取用户在线时长统计
     * @return
     */
    protected Map<String, List<DateTs>> dataCach2() {
        Map<String, List<String>> map = getOsAndUid();
        List<DateTs> data = new ArrayList<>(32);
        Map<String, List<DateTs>> result = new HashMap<>(32);
        StatisticQueryParameter statisticQueryParameter = new StatisticQueryParameter();
        statisticQueryParameter.setBegin_daily(getOnedayafter1(0));
        statisticQueryParameter.setEnd_daily(getOnedayafter1(1));
        statisticQueryParameter.setTime_type(1);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String uid : entry.getValue()) {
                statisticQueryParameter.setUid(uid);
                statisticQueryParameter.setOs_key(entry.getKey());
                data = userStaticsService.getOneUidTs(statisticQueryParameter);
                result.put(entry.getKey() + ":" + uid+":user_ts", data);
            }
        }
        return result;
    }

    /**
     * 获取用户基本信息
     * @return
     */
    protected Map<String, UserProfileStatistics> dataCach3() {
        Map<String, List<String>> map = getOsAndUid();
        UserProfileStatistics data = null;
        Map<String, UserProfileStatistics> result = new HashMap<>(32);
        StatisticQueryParameter statisticQueryParameter = new StatisticQueryParameter();
        statisticQueryParameter.setBegin_daily(getOnedayafter1(0));
        statisticQueryParameter.setEnd_daily(getOnedayafter1(1));
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String uid : entry.getValue()) {
                statisticQueryParameter.setUid(uid);
                statisticQueryParameter.setOs_key(entry.getKey());
                data = userStaticsService.findUserBasic(statisticQueryParameter);
                result.put(entry.getKey() + ":" + uid+":user_basic", data);
            }
        }
        return result;
    }

    /**
     * 获取用户岗位学习时长
     * @return
     */
    protected  Map<String, List<PostKnowledgeTs>>  dataCach4() {
        Map<String, List<String>> map = getOsAndUid();
        List<PostKnowledgeTs> data = new ArrayList<>(32);
        Map<String, List<PostKnowledgeTs>> result = new HashMap<>(32);
        StatisticQueryParameter statisticQueryParameter = new StatisticQueryParameter();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String uid : entry.getValue()) {
                statisticQueryParameter.setUid(uid);
                statisticQueryParameter.setOs_key(entry.getKey());
                data = userStaticsService.getUserPostKnTs(statisticQueryParameter);
                result.put(entry.getKey() + ":" + uid+":user_post_kn_ts", data);
            }
        }
        return result;
    }


    // 获得所有os对应的用户id
    private Map<String, List<String>> getOsAndUid() {
        Map<String, List<String>> uids = dataCacheDao.getAllUid();
        return uids;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static Integer getOnedayafter1(int n) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(cal.getTime());
            cal.add(5, n);
        } catch (Exception e) {
            log.error("getOnedayafter1 meet error:{}", e);
        }
        return Integer.parseInt(sdf.format(cal.getTime()));
    }
}
