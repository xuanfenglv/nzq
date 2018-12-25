package com.xuanfeng.nzq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = {"com.xuanfeng.nzq.domain.mapper","com.xuanfeng.nzq.domain.dao"})
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.xuanfeng.nzq"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}