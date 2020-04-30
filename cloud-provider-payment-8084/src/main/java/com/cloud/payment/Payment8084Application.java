package com.cloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.payment
 * @Author xuzhenkui
 * @Date 2020/4/29 17:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cloud.payment.module.*.mapper")
public class Payment8084Application {
    public static void main(String[] args) {
        SpringApplication.run(Payment8084Application.class,args);
    }
}
