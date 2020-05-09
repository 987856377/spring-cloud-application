package com.nacos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.nacos.config
 * @Author xuzhenkui
 * @Date 2020/5/2 11:40
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Config7131Application {
    public static void main(String[] args) {
        SpringApplication.run(Config7131Application.class,args);
    }
}
