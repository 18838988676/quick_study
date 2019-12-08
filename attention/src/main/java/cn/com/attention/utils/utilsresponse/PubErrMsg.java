package cn.com.attention.utils.utilsresponse;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
public enum PubErrMsg {
    PUBLIC_ERROR_IsError(23, "这是一个错误"),
    PUBLIC_ERROR_Null(13, "这是空指针异常了"),
    PUBLIC_ERROR_QXBZ(01, "权限不足"),
    PUBLIC_ERROR_NICAI(74, "nicain");

    private int errorCode;
    private String errorMsg;

    private PubErrMsg(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
