//package checkos;
//
//import com.qcloud.cmq.Account;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//@Configuration
//public class CmqUtils {
//    private String secretId = null;
//    private String secretKey = null;
//    private String endpoint = null;
//    private String topicNamePre = null;
//
//    @Autowired
//    private Environment env;
//
//    @PostConstruct
//    public void init() {
//        this.endpoint = env.getProperty("qcloud.topic.endpoint");
//        this.secretId = env.getProperty("qcloud.secretid");
//        this.secretKey = env.getProperty("qcloud.secretkey");
//        this.topicNamePre = env.getProperty("topic.name.pre", "");
//        log.info("------>>>cmq msg : endpoint->{},secret_id->{},secret_key->{},pre->{}", endpoint, secretId, secretKey, topicNamePre);
//    }
//
//    public Account initAccount() {
//        Account account = null;
//        try {
//            account = new Account(endpoint, secretId, secretKey);
//        } catch (Exception e) {
//            log.error("initAccount  error ！", e);
//        }
//        return account;
//    }
//}
