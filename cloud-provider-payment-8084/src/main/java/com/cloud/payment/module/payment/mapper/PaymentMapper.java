package com.cloud.payment.module.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.payment.module.payment.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
@Repository
public interface PaymentMapper extends BaseMapper<Payment> {
    boolean create(@Param("mapper/payment") Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
