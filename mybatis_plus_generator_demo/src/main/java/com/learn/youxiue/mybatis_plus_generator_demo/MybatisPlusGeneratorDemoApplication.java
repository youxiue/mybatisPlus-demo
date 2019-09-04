package com.learn.youxiue.mybatis_plus_generator_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.youxiue.mybatis_plus_generator_demo.mapper")
public class MybatisPlusGeneratorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusGeneratorDemoApplication.class, args);
    }

}
