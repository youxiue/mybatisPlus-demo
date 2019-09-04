package com.learn.youxiue.mybatis_plus.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xfb
 * @projectName: mybatis_plus->User
 * @description: TODO
 * @date: 2019/09/02 15:57
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
