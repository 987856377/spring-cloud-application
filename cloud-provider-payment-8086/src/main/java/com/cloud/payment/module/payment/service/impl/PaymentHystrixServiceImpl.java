package com.cloud.payment.module.payment.service.impl;

import com.cloud.payment.module.payment.entity.Payment;
import com.cloud.payment.module.payment.mapper.PaymentMapper;
import com.cloud.payment.module.payment.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.payment.module.payment.service
 * @Author xuzhenkui
 * @Date 2020/4/30 8:01
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PaymentMapper paymentMapper;

//    ---------------------   服务降级 fallback   ---------------------

    public Payment getPaymentInfo(Long id){
        return paymentMapper.getPaymentById(id);
    }

    @HystrixCommand(fallbackMethod = "getPaymentInfoTimeOutHandler",commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public Payment getPaymentInfoTimeOut(Long id){
        try {
            TimeUnit.SECONDS.sleep(5);    // 超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int age = 10 / 0; // 异常
        return paymentMapper.getPaymentById(id);
    }

    public Payment getPaymentInfoTimeOutHandler(Long id){
        logger.error("[cloud-hystrix-payment-service] Invoke --- getPaymentInfoTimeOutHandler --- Time Out");
        return null;
    }

//    ---------------------   服务熔断   ---------------------

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")     // 失败率达到多少后跳闸
    })
    public Payment paymentCircuitBreaker(Long id){
        if (id < 0){
            throw new RuntimeException("paymentCircuitBreaker: id: " + id + " < 0");
        }
        return paymentMapper.getPaymentById(id);
    }

    public Payment paymentCircuitBreakerFallBack(Long id){
        logger.error("[cloud-hystrix-payment-service] Invoke --- paymentCircuitBreakerFallBack --- " + "参数: id: " + id + " < 0");
        return null;
    }
}
