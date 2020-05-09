package com.cloud.order;

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
 * @Package com.cloud.order
 * @Author xuzhenkui
 * @Date 2020/4/29 17:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cloud.order.module.*.mapper")
public class Order8097Application {
    public static void main(String[] args) {
        SpringApplication.run(Order8097Application.class, args);
    }

    @Bean
    @LoadBalanced
    RestOperations restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
