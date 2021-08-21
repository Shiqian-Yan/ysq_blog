package com.yanshiqian.frontservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.yanshiqian"})
@MapperScan("com.yanshiqian.frontservice.mapper")
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class,args);
    }
}
