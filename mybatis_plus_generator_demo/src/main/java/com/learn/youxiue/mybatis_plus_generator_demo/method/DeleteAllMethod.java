package com.learn.youxiue.mybatis_plus_generator_demo.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author: xfb
 * @projectName: mybatis_plus->DeleteAllMethod
 * @description: TODO
 * @date: 2019/09/04 16:48
 */
public class DeleteAllMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //执行的sql
        String sql = " delete from "+ tableInfo.getTableName();
        //mapper接口方法名
        String methodName = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return addDeleteMappedStatement(mapperClass,methodName,sqlSource);
    }
}
