package cn.com.attention.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/7.
 * @Description:
 */
@Data
@Setter
@Getter
public class Person {
    private int id;
    private String name;
    private Date birthday;
    private Boolean isMarry;



}
