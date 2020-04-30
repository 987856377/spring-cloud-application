package com.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.hystrix
 * @Author xuzhenkui
 * @Date 2020/4/30 11:55
 */
@SpringBootApplication
@EnableHystrixDashboard
public class Dashboard7001Application {
    public static void main(String[] args) {
        SpringApplication.run(Dashboard7001Application.class,args);
    }
}
