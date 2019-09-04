package com.learn.youxiue.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.youxiue.mybatis_plus.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: xfb
 * @projectName: mybatis_plus->EmployeeMapper
 * @description: TODO
 * @date: 2019/09/02 16:38
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("select * from tbl_employee")
    List<Employee> findEmployeeList();

    @Select("select * from tbl_employee where gender = #{gender}")
    IPage<Employee> selectPageVo(Page<Employee> page, @Param("gender") String gender);
}
