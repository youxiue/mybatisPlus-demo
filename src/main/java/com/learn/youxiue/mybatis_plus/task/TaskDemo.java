package com.learn.youxiue.mybatis_plus.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: xfb
 * @projectName: mybatis_plus->TaskDemo
 * @description: TODO
 * @date: 2019/09/03 15:16
 */
@Component
public class TaskDemo {

    // 定时任务
    @Scheduled(fixedDelay = 3000) //fixedDelay 每隔多少秒执行一次 3秒
    public void sendEmail(){
        System.out.println("定时任务: 发送邮件!");
    }
}
