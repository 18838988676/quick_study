package cn.com.attention.utils.utilsresponse;

import java.text.MessageFormat;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
public interface BusinessExceptionAssert extends IExceptionEnum, BaseAssert {
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }
}
