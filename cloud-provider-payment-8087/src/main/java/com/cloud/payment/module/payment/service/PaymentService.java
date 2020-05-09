package com.cloud.payment.module.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.payment.module.payment.entity.Payment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
public interface PaymentService extends IService<Payment> {
    boolean create(Payment payment);

    Payment getPaymentById(Long id);
}
