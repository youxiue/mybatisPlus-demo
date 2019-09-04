package com.learn.youxiue.mybatis_plus_generator_demo.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.learn.youxiue.mybatis_plus_generator_demo.method.DeleteAllMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: xfb
 * @projectName: mybatis_plus->MySqlInjector
 * @description: TODO
 * @date: 2019/09/04 16:56
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //加入自定义的删除所有的方法
        methodList.add(new DeleteAllMethod());

        methodList.add(new InsertBatchSomeColumn(t->!t.isLogicDelete()));
        return methodList;
    }
}
