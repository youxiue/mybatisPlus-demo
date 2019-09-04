package com.learn.youxiue.mybatis_plus_generator_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author: xfb
 * @projectName: mybatis_plus->MyMapper
 * @description: TODO
 * @date: 2019/09/04 17:00
 */
public interface MyMapper<T> extends BaseMapper<T> {
    int deleteAll();

    int insertBatchSomeColumn(List<T> list);
}
