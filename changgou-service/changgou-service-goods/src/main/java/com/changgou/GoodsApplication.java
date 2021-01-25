package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName GoodsApplication
 * @Description
 * @Author 传智播客
 * @Date 11:27 2020/12/8
 * @Version 2.1
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})  // tk包  开启dao层的包扫描
public class GoodsApplication {


    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
