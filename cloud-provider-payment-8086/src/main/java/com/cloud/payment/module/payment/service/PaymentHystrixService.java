package com.cloud.payment.module.payment.service;

import com.cloud.payment.module.payment.entity.Payment;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.payment.module.payment.service
 * @Author xuzhenkui
 * @Date 2020/4/30 8:12
 */
public interface PaymentHystrixService {
    Payment getPaymentInfo(Long id);
    Payment getPaymentInfoTimeOut(Long id);
}
