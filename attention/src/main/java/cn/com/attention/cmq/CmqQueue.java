package cn.com.attention.cmq;

import com.qcloud.cmq.Account;
import com.qcloud.cmq.Message;
import com.qcloud.cmq.Queue;
import com.qcloud.cmq.QueueMeta;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:CmqQueue的信息类
 */
public class CmqQueue/* extends AbstractMq */{
/*    private static final Logger LOGGER = LoggerFactory.getLogger(CmqQueue.class);
    private Account account;
    private Queue queue;

    public CmqQueue(MqAccount mqAccount, String queueName) {
        mqAccount = (MqAccount) Preconditions.checkNotNull(mqAccount);
        Preconditions.checkNotNull(queueName);
        queueName = this.getNameWithSuffix(queueName);
        this.init(mqAccount, queueName);
    }

    private void init(MqAccount mqAccount, String queueName) {
        this.account = new Account(mqAccount.getQueueEndpoint(), mqAccount.getSecretId(), mqAccount.getSecretKey());
        ArrayList list = Lists.newArrayList();

        try {
            this.account.listQueue(queueName, -1, -1, list);
            long count = list.stream().filter((name) -> {
                return queueName.equalsIgnoreCase(name);
            }).count();
            if (count == 0L) {
                QueueMeta meta = new QueueMeta();
                this.account.createQueue(queueName, meta);
            } else {
                LOGGER.warn("cmq queueName  {}  has exist", queueName);
            }

            this.queue = this.account.getQueue(queueName);
        } catch (Exception var7) {
            LOGGER.error("cmq createQueue error", var7);
            throw new RuntimeException(var7);
        }
    }

    public void setQueueAttr(QueueMeta meta) {
        try {
            this.queue.setQueueAttributes(meta);
        } catch (Exception var3) {
            LOGGER.error("cmq setQueueAttr error", var3);
        }

    }

    public String sendMsg(String msg) {
        try {
            return this.queue.sendMessage(msg);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},sendMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
            return null;
        }
    }

    public List<String> batchSendMsg(List<String> msgs) {
        try {
            return this.queue.batchSendMessage(msgs);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},batchSendMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
            return null;
        }
    }

    public Message receiveMsg() {
        Message message = null;

        try {
            message = this.queue.receiveMessage(10);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},receiveMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
        }

        return message;
    }

    public List<Message> batchReceiveMsg(int numOfMsg) {
        try {
            return this.queue.batchReceiveMessage(numOfMsg, 10);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},batchReceiveMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
            return null;
        }
    }

    public void deleteMsg(String receiHandle) {
        try {
            this.queue.deleteMessage(receiHandle);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},deleteMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
        }

    }

    public void batchDeleteMsg(List<String> receiHandles) {
        try {
            this.queue.batchDeleteMessage(receiHandles);
        } catch (Exception var3) {
            LOGGER.error("cmq queuename:{},batchDeleteMsg errorinfo:{},error:", new Object[]{this.exchangeName, var3.toString(), var3});
        }

    }
}

public abstract class AbstractMq {
    protected String exchangeName;
    protected String exchangeType = "topic";

    public AbstractMq() {
    }

    protected String getExchangeType() {
        return this.exchangeType;
    }

    protected String getNameWithSuffix(String name) {
        return !DeveloperUtil.isLocalDebug() ? name + "_" + Util.runEvn : name + "_local";
    }*/
}