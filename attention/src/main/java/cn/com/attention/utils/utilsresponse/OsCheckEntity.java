package cn.com.attention.utils.utilsresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/10.
 * @Description: 传输删除的osk的值，或增加的osk 及其将要添加的任务类型；
 */
@Data
@AllArgsConstructor
@ToString
public class OsCheckEntity {

    // isAdd:true 此oskey是新增的，false是删除的
    private Boolean isAdd;

    //osk
    private String osk;

    // 新增的oskey,  后期用枚举。。
    private Integer jobType;

}
