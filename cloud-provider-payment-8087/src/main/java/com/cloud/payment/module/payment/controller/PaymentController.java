package com.cloud.payment.module.payment.controller;


import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.payment.module.payment.entity.Payment;
import com.cloud.payment.module.payment.service.PaymentService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/create")
    public ResultJson create(@RequestBody Payment payment){
        if (paymentService.create(payment)){
            return ResultJson.success("支付成功");
        } else {
            return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR,"支付失败");
        }
    }

    @RequestMapping("/getPaymentById/{id}")
    public ResultJson getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null){
            return ResultJson.success(payment);
        } else {
            return ResultJson.failure(ResultCode.GONE,"未查询到该支付信息");
        }
    }

    @RequestMapping("/payment/discovery")
    public ResultJson discovery(){
        Map<String,Object> map = new HashMap<>();
        List<String> serviceList = discoveryClient.getServices();
        map.put("services",serviceList);
        serviceList.forEach(s -> {
            map.put(s,discoveryClient.getInstances(s.toUpperCase()));
        });
        return ResultJson.success(map);
    }
}
