package com.cloud.order.module.order.service;

import com.cloud.common.serializable.ResultJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.order.module.order.service
 * @Author xuzhenkui
 * @Date 2020/4/29 21:39
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @RequestMapping("/payment/getPaymentById/{id}")
    ResultJson getPaymentById(@PathVariable("id") Long id);
}
