package com.cloud.order.module.order.controller;


import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.order.module.order.entity.Order;
import com.cloud.order.module.order.entity.Payment;
import com.cloud.order.module.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.annotation.Resource;

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

    @RequestMapping("/create")
    public ResultJson<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(INVOKE_URL+"/payment/create",payment, ResultJson.class);
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
}
