//package checkos;
//
//import com.botbrain.timer.core.model.OsCheckEntity;
//import com.qcloud.cmq.Account;
//import com.qcloud.cmq.CMQServerException;
//import com.qcloud.cmq.Json.JSONObject;
//import com.qcloud.cmq.Topic;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.annotation.Resource;
//
//
//@Slf4j
////@Configuration
//public class SendMQ {
//
//    private static final String topicName = "zy-topic-schedule-checkosk";
//    @Resource
//    private CmqUtils cmqService;
//
//    private void sendMsg() {
//        Account account = cmqService.initAccount();
//        try {
//            // 获得队列实例（此处直接使用现有队列进行操作，也可按照上面的注释创建队列）
//            Topic topic = account.getTopic(topicName);
//            // 发送单条信息
//            OsCheckEntity osCheckEntity = new OsCheckEntity(true, "2w", 1);
//            JSONObject json = new JSONObject(osCheckEntity);
//            String msgId = topic.publishMessage(json.toString(), "");
//            System.out.println("==> send success! msg_id:" + msgId);
//
//            //批量操作
//            //批量发送消息
////            System.out.println("---------------batch send message ...---------------");
////            ArrayList<String> vtMsgBody = new ArrayList<String>();
////            String msgBody = "hello world,this is cmq sdk for java 1";
////            vtMsgBody.add(msgBody);
////            msgBody = "hello world,this is cmq sdk for java 2";
////            vtMsgBody.add(msgBody);
////            msgBody = "hello world,this is cmq sdk for java 3";
////            vtMsgBody.add(msgBody);
////            List<String> vtMsgId = topic.batchPublishMessage(vtMsgBody);
////            for(int i=0;i<vtMsgBody.size();i++){
////                System.out.println("[" + vtMsgBody.get(i) + "] sent");}
////            for(int i=0;i<vtMsgId.size();i++){
////                System.out.println("msgId:" + vtMsgId.get(i));}
//
//        } catch (CMQServerException e1) {
//            System.out.println("Server Exception, " + e1.toString());
//        } catch (Exception e) {
//            System.out.println("error..." + e.toString());
//        }
//
//    }
//
//
//}
