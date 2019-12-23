package com.example.webtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull(message = "id不能为空")
    private String id;
    @NotBlank(message = "name不能为空")
    private String name;
    @Max(value = 30,message = "不能超过30")
    @Min(value = 20,message = "不能小于20")
    private Integer age;

}
