package com.clj.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.clj.spring.mapper")
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
