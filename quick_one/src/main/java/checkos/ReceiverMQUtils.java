package checkos;

import com.botbrain.timer.core.model.OsCheckEntity;
import com.qcloud.cmq.Account;
import com.qcloud.cmq.CMQServerException;
import com.qcloud.cmq.Json.JSONObject;
import com.qcloud.cmq.Message;
import com.qcloud.cmq.Queue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:
 */
@Slf4j
@Configuration
public class ReceiverMQUtils {

    @Autowired
    private Environment env;
    //队列名
    private static final String queueName = "zy-queue-schedule-checkosk";

    @Resource
    private CmqUtils cmqService;

    private ScheduledExecutorService scheduledExecutorServiceCheck= new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            int num = 0;
            return new Thread(r, "check-osKey-changeThread：" + num++);
        }
    });

    @Autowired
    private CheckOs checkOs;

    @PostConstruct
    private void initListens() {
        scheduledExecutorServiceCheck.scheduleWithFixedDelay(() -> {
            receiverMsg();
        }, 20, 30, TimeUnit.SECONDS);
        log.info("init statr ....success...");
    }

    //接收单条消息
    private void receiverMsg() {
        OsCheckEntity osCheckEntity = null;
        log.info("start receiverMsg...");
        Account account = cmqService.initAccount();
        Queue queue = account.getQueue(queueName);
        log.info("get account: --->:{}   queue:----->:{}", account);
        Message msg=null;
        try {
            msg = queue.receiveMessage(20);
            if (msg != null) {
                log.info("msg:-----:", msg);
                if (StringUtils.contains(msg.msgBody, "{")) {
                    JSONObject jsonObj = new JSONObject(msg.msgBody);
                    osCheckEntity = new OsCheckEntity(jsonObj.getBoolean("isAdd"), jsonObj.getString("osk"), jsonObj.getInt("osType"));
                    checkOs.initJudge(osCheckEntity);
                }
                queue.deleteMessage(msg.receiptHandle);
                log.info("delete msg...receiptHandle:" + msg.receiptHandle + " deleted");
            }
        } catch (Exception e) {
            if (e instanceof CMQServerException && ((CMQServerException) e).getErrorCode() == 7000) {
                log.info("this get null msg ,continue.......");
            } else {
                log.error("get message is error :{}", e.getMessage());
            }
        }
    }

    //接收多条消息
    private void receiverMultsMsg() {
        Account account = cmqService.initAccount();
        Queue queue = account.getQueue("");
        log.info("get account: --->:{}   queue:----->:{}", account);
        try {
            //批量接收消息
            ArrayList<String> vtReceiptHandle = new ArrayList<String>(); //保存服务器返回的消息句柄，用于删除消息
            System.out.println("---------------batch recv message ...---------------");
            List<Message> msgList = queue.batchReceiveMessage(10, 10);
            System.out.println("recv msg count:" + msgList.size());
            for (int i = 0; i < msgList.size(); i++) {
                Message msg1 = msgList.get(i);
                System.out.println("msgId:" + msg1.msgId);
                System.out.println("msgBody:" + msg1.msgBody);
                System.out.println("receiptHandle:" + msg1.receiptHandle);
                System.out.println("enqueueTime:" + msg1.enqueueTime);
                System.out.println("nextVisibleTime:" + msg1.nextVisibleTime);
                System.out.println("firstDequeueTime:" + msg1.firstDequeueTime);
                System.out.println("dequeueCount:" + msg1.dequeueCount);
                System.out.println();

                vtReceiptHandle.add(msg1.receiptHandle);
            }

//            批量删除消息
            System.out.println("---------------batch delete message ...---------------");
            queue.batchDeleteMessage(vtReceiptHandle);
            for (int i = 0; i < vtReceiptHandle.size(); i++)
                System.out.println("receiptHandle:" + vtReceiptHandle.get(i) + " deleted");
        } catch (Exception e) {
            log.error("get message is error :{}", e.getMessage());
        }
    }


}
