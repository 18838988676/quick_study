package cn.com.attention.utils.utilsresponse;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */

public enum PubExcepEnum implements BusinessExceptionAssert {
    PUBLIC_Execution01(5654, "网络被端口"),
    PUBLIC_FDUOFJDFlu(1240, "name irregularitirqerqes"),
    PUBLIC_KODSF(326887, "contedfasdrwent contains illegal worfasdd"),
    PUBLIC_POUIOUF(2346456, "field exceeds length limitfwerq"),
    PUBLIC_RUEHJJKDHF(6697879, "illegadfasdl reqerqewuest"),
    PUBLIC_FHDNXH(32456778, "networkfadsfwerq exception=daf, please try agaadfain later23434");

    public int code;
    public String message;

    private PubExcepEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
