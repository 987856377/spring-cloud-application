package com.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.config
 * @Author xuzhenkui
 * @Date 2020/5/1 13:20
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class Config7021Application {
    public static void main(String[] args) {
        SpringApplication.run(Config7021Application.class,args);
    }
}
