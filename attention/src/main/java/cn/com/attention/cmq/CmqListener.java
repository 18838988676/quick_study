package cn.com.attention.cmq;

import com.qcloud.cmq.Message;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:CMQ消息监听类
 */
@Slf4j
@Component
public class CmqListener implements ApplicationContextAware, ApplicationListener<ApplicationEvent> {

    @Setter
    private ApplicationContext applicationContext;
    @Autowired
    private TaskExecutor taskExecutor;

    private boolean isStart = false;

    /**
     * 获取所有的需要监听mq的类，以及注册的mq
     *
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        HashMap<String, IBaseCmqHandler> map = new HashMap<>(16);
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(IzkQueue.class);
        beanMap.forEach((key, value) -> {
            IzkQueue annotation = value.getClass().getAnnotation(IzkQueue.class);
            String queue = annotation.queueName();
            map.put(queue, (IBaseCmqHandler) value);
        });
        if (!isStart) {
            isStart = true;
            if (!CollectionUtils.isEmpty(map)) {
                taskExecutor.execute(() -> executeQueueHandler(map));
            }
        }
    }

    private void executeQueueHandler(HashMap<String, IBaseCmqHandler> map) {
        map.forEach((queueName, bean) -> {
            taskExecutor.execute(() -> receiveCmqMessage(queueName, bean));
        });

    }

    /**
     * 功能描述 : 将队列与对应的消息处理器进行匹配，并进行消息消费
     *
     * @param queueName  : queue name
     * @param cmqHandler : 具体的消息处理器
     * @return
     * @created 2019-07-14 16:55
     */
    private void receiveCmqMessage(String queueName, IBaseCmqHandler cmqHandler) {
        try {
            while (true) {
                // 睡眠 释放cpu资源
                Thread.sleep(10);
                CmqQueue cmqQueue = applicationContext.getBean(queueName, CmqQueue.class);
                Message message = cmqQueue.receiveMsg();

                if (null != message) {
                    log.info("时间：{}， 队列：{}, 收到消息：{}", LocalDateTime.now(), queueName, message.msgBody);
                    if (!StringUtils.isEmpty(message.msgBody) && !StringUtils.isEmpty(message.receiptHandle)) {
                        taskExecutor.execute(() -> {
                            try {
                                // 处理消息
                                if (cmqHandler.onMessage(queueName, message)) {
                                    // 消费成功 删除消息
                                    cmqQueue.deleteMsg(message.receiptHandle);
                                } else {
                                    taskExecutor.execute(() -> receiveCmqMessage(queueName, cmqHandler));
                                }
                            } catch (Exception e) {
                                log.error("消息处理失败 --> 队列名：{}, 已进行自动补偿,Exception:", queueName, e);
                                taskExecutor.execute(() -> receiveCmqMessage(queueName, cmqHandler));
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            log.error("消息执行失败 --> 队列名：{}, 已进行自动补偿,Exception:", queueName, e);
            taskExecutor.execute(() -> receiveCmqMessage(queueName, cmqHandler));
        }
    }
}