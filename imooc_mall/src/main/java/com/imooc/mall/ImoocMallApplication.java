package com.imooc.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.imooc.mall.model.dao")
@SpringBootApplication
public class ImoocMallApplication{

    public static void main(String[] args) {
        SpringApplication.run(ImoocMallApplication.class, args);
    }

}
