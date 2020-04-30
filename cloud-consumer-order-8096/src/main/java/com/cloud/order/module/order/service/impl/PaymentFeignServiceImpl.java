package com.cloud.order.module.order.service.impl;

import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.order.module.order.service.PaymentFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.order.module.order.service.impl
 * @Author xuzhenkui
 * @Date 2020/4/30 10:30
 */
@Component
public class PaymentFeignServiceImpl implements PaymentFeignService{    // 服务调用失败,指定 fallback 方法

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResultJson getPaymentById(Long id) {
        logger.error("[cloud-order-service] Invoke --- getPaymentById --- SERVICE UNAVAILABLE");
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResultJson getPaymentInfo(Long id) {
        logger.error("[cloud-order-service] Invoke --- getPaymentInfo --- SERVICE UNAVAILABLE");
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResultJson getPaymentInfoTimeOut(Long id) {
        logger.error("[cloud-order-service] Invoke --- getPaymentInfoTimeOut --- SERVICE UNAVAILABLE");
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResultJson paymentCircuitBreaker(Long id) {
        logger.error("[cloud-order-service] Invoke --- paymentCircuitBreaker --- SERVICE UNAVAILABLE");
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }
}
