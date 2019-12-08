package cn.com.attention.entity;

import lombok.*;

import java.util.Date;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {
    private String name;
    private String id;
    private int age;
    private String sex;
    private Date d;

}
