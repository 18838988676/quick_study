package cn.com.attention.utils.utilsresponse;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected IExceptionEnum exceptionEnum;
    protected Object[] args;

    public BaseException(IExceptionEnum responseEnum) {
        super(responseEnum.getMessage());
        this.exceptionEnum = responseEnum;
    }

    public BaseException(final int code, final String msg) {
        super(msg);
        this.exceptionEnum = new IExceptionEnum() {
            public int getCode() {
                return code;
            }

            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(IExceptionEnum responseEnum, Object[] args, String message) {
        super(message);
        this.exceptionEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IExceptionEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.exceptionEnum = responseEnum;
        this.args = args;
    }

    public IExceptionEnum getResponseEnum() {
        return this.exceptionEnum;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
