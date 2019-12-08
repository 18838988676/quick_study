package cn.com.attention.utils.utilsresponse;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BusinessException(IExceptionEnum exceptionEnum, Object[] args, String message) {
        super(exceptionEnum, args, message);
    }
}
