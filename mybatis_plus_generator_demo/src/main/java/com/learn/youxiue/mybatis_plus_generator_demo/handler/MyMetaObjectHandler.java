package com.learn.youxiue.mybatis_plus_generator_demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: xfb
 * @projectName: mybatis_plus->MyMetaObjectHandler
 * @description: 自定义 自动填充处理
 * @date: 2019/09/04 14:13
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        LocalDateTime now = LocalDateTime.now();
        Date date = new Date();
        System.out.println("插入时间自动填充: "+date);
        //设置创建时间为 当前时间
        this.setInsertFieldValByName("createTime",date,metaObject);
        this.setInsertFieldValByName("deleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        // 判断如果之前没有设置值得话 使用自动填充
        if(val == null){
            Date date = new Date();
//            LocalDateTime now = LocalDateTime.now();
            System.out.println("修改时间自动填充: "+date);
            //设置修改时间为当前时间
            this.setUpdateFieldValByName("updateTime",date,metaObject);
        }
    }

    /**
     * 还可以 获取session中的用户信息 自动填充添加人 和修改人
     */
}
