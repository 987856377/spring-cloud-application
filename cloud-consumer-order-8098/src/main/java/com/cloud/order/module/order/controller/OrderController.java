package com.cloud.order.module.order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.order.module.order.entity.Order;
import com.cloud.order.module.order.entity.Payment;
import com.cloud.order.module.order.service.OrderService;
import com.cloud.order.module.order.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${spring.cloud.nacos.invoke-url}")
    private String INVOKE_URL;

    @Resource
    private RestOperations restTemplate;

    @Resource
    private OrderService orderService;

    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping("/create")
//    public ResultJson<Payment> create(@RequestBody Payment payment){
//        return restTemplate.postForObject(INVOKE_URL+"/payment/create",payment, ResultJson.class);
    public ResultJson create(@RequestBody Order order){
        orderService.create(order);
        return ResultJson.success("订单创建成功");
    }

    @RequestMapping("/getPaymentById/{id}")
    public ResultJson<Payment> getPaymentById(@PathVariable Long id){
        return restTemplate.postForObject(INVOKE_URL+"/payment/getPaymentById/"+id,id, ResultJson.class);
    }

    @RequestMapping("/getOrderById/{id}")
    public ResultJson getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        if (order!=null){
            return ResultJson.success(order);
        } else {
            return ResultJson.failure(ResultCode.GONE,"未查询到该订单");
        }
    }

    @RequestMapping("/getPaymentInfo/{id}")
    @SentinelResource(value = "getPaymentInfo", fallback = "fallBackHandler", blockHandler = "exceptionHandler")    // fallback 只负责业务异常
    public ResultJson<Payment> getPaymentInfo(@PathVariable Long id){
        if (id == 2){
            throw new RuntimeException("error");
        }
        return restTemplate.postForObject(INVOKE_URL+"/payment/getPaymentById/"+id,id, ResultJson.class);
    }

    public ResultJson fallBackHandler(Long id){
        return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public ResultJson exceptionHandler(@PathVariable Long id, BlockException e){
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE,e.getClass().getCanonicalName());
    }

    @RequestMapping("/getFeignPaymentById/{id}")
    public ResultJson<Payment> getFeignPaymentById(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @RequestMapping("/getFeignPaymentInfo/{id}")
    public ResultJson getFeignPaymentInfo(@PathVariable Long id){
        return ResultJson.success(paymentFeignService.getPaymentInfo(id));
    }
}
