package com.cn.logtet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 日志测试使用
 *
 * @author MoCha
 * @date 2019/5/23
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggerTest {
    @Test
    public void testLogging() {
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
    }
}