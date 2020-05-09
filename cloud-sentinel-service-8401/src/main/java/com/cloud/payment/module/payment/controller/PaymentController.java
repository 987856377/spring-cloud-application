package com.cloud.payment.module.payment.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.payment.handler.CustomBlockHandler;
import com.cloud.payment.module.payment.entity.Payment;
import com.cloud.payment.module.payment.service.PaymentService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

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

//    热点规则, QPS 超过阈值, 进行降级, 调用自定义方法处理
    @RequestMapping("/doException")
    @SentinelResource(value = "doException", blockHandler = "doExceptionHandler")
    public ResultJson doException(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
        return ResultJson.failure(ResultCode.GONE,"0.0");
    }

    public ResultJson doExceptionHandler(String p1, String p2, BlockException e){
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE,e.getClass().getCanonicalName());
    }

    @RequestMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handleException2")
    public ResultJson customBlockHandler(){
        return ResultJson.failure(ResultCode.SUCCESS);
    }
}
