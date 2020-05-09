package com.cloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;

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
public class Sentinel9004Application {
    public static void main(String[] args) {
        SpringApplication.run(Sentinel9004Application.class,args);
    }

    @Bean
    @LoadBalanced
    RestOperations restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
