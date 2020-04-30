package com.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.eureka
 * @Author xuzhenkui
 * @Date 2020/4/29 10:56
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka5002Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka5002Application.class,args);
    }
}
