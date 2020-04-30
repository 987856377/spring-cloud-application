package com.cloud.order.module.order.controller;


import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;
import com.cloud.order.module.order.entity.Order;
import com.cloud.order.module.order.entity.Payment;
import com.cloud.order.module.order.service.OrderService;
import com.cloud.order.module.order.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@DefaultProperties(defaultFallback = "paymentGlobalFallBack")   // 指定全局fallback异常处理方法
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.cloud.eureka.invoke-url}")
    private String INVOKE_URL;

    @Resource
    private RestOperations restTemplate;

    @Resource
    private OrderService orderService;

    @Resource
    private PaymentFeignService paymentFeignService;

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

    @RequestMapping("/getFeignPaymentById/{id}")
    public ResultJson<Payment> getFeignPaymentById(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @RequestMapping("/getPaymentInfo/{id}")
    public ResultJson getPaymentInfo(@PathVariable Long id){
        return ResultJson.success(paymentFeignService.getPaymentInfo(id));
    }

//    @HystrixCommand(fallbackMethod = "getPaymentInfoTimeOutHandler",commandProperties = {     // 使用自定义 fallback
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand     // 使用全局 fallback
    @RequestMapping("/getPaymentInfoTimeOut/{id}")
    public ResultJson getPaymentInfoTimeOut(@PathVariable Long id){
        return paymentFeignService.getPaymentInfoTimeOut(id);
    }

    public ResultJson getPaymentInfoTimeOutHandler(Long id){
        logger.error("[cloud-order-service] Invoke --- getPaymentInfoTimeOutHandler --- Time Out");
        return ResultJson.failure(ResultCode.GONE);
    }

    // 全局 fallback 方法, 进行服务降级
    public ResultJson paymentGlobalFallBack(){
        logger.error("[cloud-order-service] Invoke --- paymentGlobalFallBack --- SERVICE UNAVAILABLE");
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }


    @HystrixCommand     // 使用全局 fallback
    @RequestMapping("/paymentCircuitBreaker/{id}")
    public ResultJson paymentCircuitBreaker(@PathVariable Long id){
        return paymentFeignService.paymentCircuitBreaker(id);
    }
}
