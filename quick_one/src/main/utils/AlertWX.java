import com.alibaba.fastjson.JSONObject;
import com.botbrain.timer.core.model.XxlJobInfo;
import com.botbrain.timer.core.model.XxlJobLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/5.
 * @Description: 微信告警对接
 */
@Component
@Configuration
public class AlertWX {
    private static Logger logger = LoggerFactory.getLogger(AlertWX.class);

    private RestTemplate qywxRestTemplate=new RestTemplate();

    @Autowired
    private Environment environment;

    private static String webhookUrl = "";

    private static final String markdownParam = "任 务ID:%s   \n" +
            "任务名称：%s  \n" +
            "报警时间：%s  \n" +
            "请求参数：%s  \n" +
            "请求结果：%s  ";

    @PostConstruct
    private void init() {
        qywxRestTemplate = qywxRestTemplate();
        webhookUrl = environment.getProperty("xxl-job-wx-webhook");
        if (StringUtils.isBlank(webhookUrl)) {
            logger.error("xxl-job,alert mail get webhookUrl is null");
        }
    }

    public HttpEntity<JSONObject> alertInfo(XxlJobInfo xxlJobInfo, XxlJobLog log) {
        String str = String.format(markdownParam, xxlJobInfo.getId(),xxlJobInfo.getJobDesc(),transDate( log.getTriggerTime()), log.getExecutorParam(), log.getHandleMsg());
        return alerm(str);
    }

    private HttpEntity<JSONObject> alerm(String str) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        AlertParam parm = new AlertParam();
        parm.setMsgtype("markdown");
        parm.setMarkdown(new AlertParam.MarkdownBean(str));
        HttpEntity<AlertParam> entity = new HttpEntity<AlertParam>(parm, headers);
        return qywxRestTemplate.exchange(webhookUrl, HttpMethod.POST, entity, JSONObject.class);
    }

    private RestTemplate qywxRestTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(30000);
        simpleClientHttpRequestFactory.setReadTimeout(30000);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }
    private String transDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
