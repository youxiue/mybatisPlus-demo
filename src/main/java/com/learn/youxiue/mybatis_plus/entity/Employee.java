package com.learn.youxiue.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: xfb
 * @projectName: mybatis_plus->Employee
 * @description: TODO
 * @date: 2019/09/02 16:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_employee")
@Accessors(chain = true)
public class Employee extends Model<Employee> {

    @TableId(value = "id",type = IdType.AUTO)
    @TableField("id")
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("email")
    private String email;
    @TableField("gender")
    private Integer gender;
    @TableField("age")
    private Integer age;
}
