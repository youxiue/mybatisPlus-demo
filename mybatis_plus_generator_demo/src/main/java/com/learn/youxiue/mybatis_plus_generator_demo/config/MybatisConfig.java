package com.learn.youxiue.mybatis_plus_generator_demo.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.statement.delete.Delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xfb
 * @projectName: mybatis_plus->MybatisConfig
 * @description: TODO
 * @date: 2019/09/04 14:59
 */
@EnableTransactionManagement //开启事务管理器
@Configuration
@MapperScan("com.learn.youxiue.mybatis_plus.mapper")
public class MybatisConfig {


    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);


        List<ISqlParser> sqlParserList = new ArrayList<>();
        /*// 攻击 SQL 阻断解析器、加入解析链  这里是阻止恶意的全表更新删除
        sqlParserList.add(new BlockAttackSqlParser() {
            @Override
            public void processDelete(Delete delete) {
                // 如果你想自定义做点什么，可以重写父类方法像这样子
                if ("user".equals(delete.getTable().getName())) {
                    // 自定义跳过某个表，其他关联表可以调用 delete.getTables() 判断
                    return ;
                }
                super.processDelete(delete);
            }
        });

        //多租户设置 设置之后 sql 语句中会增加 判断 租客属性值是否一致
        //SELECT id, deleted, create_time, name, update_time, manager_id, version, email, age FROM tbl_user WHERE id = ? AND deleted = 0 AND tbl_user.manager_id = 1088248166370832385
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            // 设置租户属性的值  动态获取 这里写死了
            @Override
            public Expression getTenantId(boolean where) {
                return new LongValue(1088248166370832385L);
            }
            // 设置租户属性字段
            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }
            //
            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
                *//*if("role".equals(tableName)){
                    return true;
                }*//*
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);*/

        // 动态表名 sql 解析器
        DynamicTableNameParser dtnp = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("tbl_user", new ITableNameHandler() {
            // 将表替换为 传入的表名
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                System.out.println("表替换为: "+ myTableName.get());
                return myTableName.get();
            }
        });
        dtnp.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dtnp);


        paginationInterceptor.setSqlParserList(sqlParserList);

        //多租户sql 解析器
        // 配置这个 会导致动态表名 sql 解析器失效
       /* paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 指定 sql 忽略租户信息的 配置
                if("com.learn.youxiue.mybatis_plus_generator_demo.mapper.UserMapper.selectById".equals(ms.getId())){
                    return true;
                }
                return false;
            }
        });*/
        //还可以 通过@SqlParser(filter=true) 排除多租户 SQL 解析




        return paginationInterceptor;
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


}
