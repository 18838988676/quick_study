package cn.com.attention.xiancehng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/11.
 * @Description:  redis相关配置
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private Environment env;


    /**
     * 组件页面配置 存库3
     * @return
     */
    @Bean(name = "operRedisTemplate")
    public RedisTemplate<String,String> operRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(jedisConnectionFactory(3));
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        return redisTemplate;
    }

    /**
     * 老运营位学习页面 存库1
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String,String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(jedisConnectionFactory(3));
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        return redisTemplate;
    }


    private JedisConnectionFactory jedisConnectionFactory(int dataBase) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(dataBase);
        config.setHostName(env.getProperty("spring.redis.host"));
        config.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
        config.setPort(Integer.valueOf(env.getProperty("spring.redis.port")));
        return new JedisConnectionFactory(config);
    }

}
