import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/5.
 * @Description:  企业微信告警
 */
@Data
public class AlertParam implements Serializable {

    private String msgtype;
    private MarkdownBean markdown;

    @AllArgsConstructor
    @Data
    public static class MarkdownBean implements Serializable {
        private String content;

    }
}
