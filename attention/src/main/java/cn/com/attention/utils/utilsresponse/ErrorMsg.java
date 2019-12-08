package cn.com.attention.utils.utilsresponse;

import lombok.Data;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
@Data
public class ErrorMsg {
    private int errorCode;
    private String errorMsg;
}