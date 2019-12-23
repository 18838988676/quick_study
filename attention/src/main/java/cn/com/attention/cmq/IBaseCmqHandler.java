package cn.com.attention.cmq;

import com.qcloud.cmq.Message;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:
 * 消息处理器抽象统一接口
 */
public interface IBaseCmqHandler {
    /**
     * 处理从cmq中获取的消息
     *
     * @param queueName : 队列名
     * @param message   : 消息体
     * @return
     */
    boolean onMessage(String queueName, Message message);
}
