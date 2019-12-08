package cn.com.attention.utils.utilsresponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */

@ApiModel(     value = "用来义返回值的对阿星的",
        description = "用来定义返回值的对象的"
)
@Data
@Setter
@Getter
@AllArgsConstructor
@SpringBootTest
public class Response<T> implements Serializable {
   //失败的
    public static final Integer ThisIsERROR = 11;
//    成功的
    public static final Integer ThisIsSUCCESS = 00;
    @ApiModelProperty(
            value = "这是状态码",
            name = "返回值code",
            required = true,
            dataType = "Integer"
    )
    //  dataType = "Integer"  String Object
    private Integer code;
    @ApiModelProperty(
            value = "这是错误信息",
            name = "这是返回值code",
            required = true,
            dataType = "String"
    )

    private long cost;

    private T data;

    private String thismsg;

    public Response() {
        this.code = ThisIsSUCCESS;
        this.thismsg = "success";
    }

    public Response(ErrorMsg error) {
        this.code = error.getErrorCode();
        this.thismsg = error.getErrorMsg();
    }

    public Response(PubErrMsg error) {
        this.code = error.getErrorCode();
        this.thismsg = error.getErrorMsg();
    }


    public Response(PubExcepEnum exception) {
        this.code = exception.getCode();
        this.thismsg = exception.getMessage();
    }

    public Response(String msg) {
        this.code = ThisIsERROR;
        this.thismsg = msg;
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.thismsg = msg;
    }
    public boolean succeeded() {
        return this.code != null && this.code != 1;
    }

    public boolean succeed() {
        return this.code != null && this.code == 0;
    }

    public String toString() {
        return "Response{code=" + this.code + ", msg='" + this.thismsg + '\'' + ", data=" + this.data + ", cost=" + this.cost + '}';
    }
}
