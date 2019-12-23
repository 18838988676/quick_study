package cn.com.attention;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/16.
 * @Description:
 */
public class temp {
    /* try {
        String key=statisticQueryParameter.getOs_key()+":"+statisticQueryParameter.getUid()+":user_keyword";
        String data=  redisTemplate.opsForValue().get(key);
        List<String> list1=  redisTemplate.opsForList().range(key, 0, -1);
        Collections.reverse(list1);
        if (CollectionUtils.isNotEmpty(list1)) {
            log.info("get cache");
            return  commonTablesResponse(statisticQueryParameter, list1.stream().map(s -> JSONObject.parseObject(s,UserProfileKeyword.class)).collect(Collectors.toList()));
        }
        log.info("no get cache");

        statisticQueryParameter.setOs_key(osKey);
        return commonTablesResponse(statisticQueryParameter, userStaticsService.getOneUidKeyword(statisticQueryParameter));

        try {
            String key=osKey+":"+statisticQueryParameter.getUid()+":"+"user_ts";
            List<String> list= redisTemplate.opsForList().range(key,0,-1);
            Collections.reverse(list);
            if(CollectionUtils.isNotEmpty(list)){
                log.info("get cache");
                return commonTablesResponse(statisticQueryParameter, list.stream().map(e->JSONObject.parseObject(e,DateTs.class)).collect(Collectors.toList()));
            }
            log.info("no get cache");
            statisticQueryParameter.setOs_key(osKey);
            return commonTablesResponse(statisticQueryParameter, userStaticsService.getOneUidTs(statisticQueryParameter));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("user_ts_distribution fail.");
        }
*/
}
