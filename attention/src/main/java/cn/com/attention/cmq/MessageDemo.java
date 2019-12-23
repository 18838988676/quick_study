package cn.com.attention.cmq;

import com.qcloud.cmq.Message;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:
 */

@IzkQueue(queueName = "queueDemo",value = "demo")
public class MessageDemo implements IBaseCmqHandler {
    @Override
    public boolean onMessage(String queueName, Message message) {
        //todo
        return false;
    }
}