package cn.com.attention.cmq;

import lombok.Data;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/9.
 * @Description:账号信息类
 */
@Data
public class MqAccount {
    private String host;
    private String port;
    private String username;
    private String password;
    private String vhost;
    private String secretId;
    private String secretKey;
    private String endpoint;
    private String queueEndpoint;
}