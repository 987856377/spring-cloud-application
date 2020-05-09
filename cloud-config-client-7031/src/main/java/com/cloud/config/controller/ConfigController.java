package com.cloud.config.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.config.controller
 * @Author xuzhenkui
 * @Date 2020/5/1 13:53
 */
@RestController
@RefreshScope
public class ConfigController {
    private Logger logger = LoggerFactory.getLogger(ConfigController.class);
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("/configInfo")
    public String getConfigInfo(){
        logger.info("--------------- " + configInfo + "---------------");
        return configInfo;
    }
}
