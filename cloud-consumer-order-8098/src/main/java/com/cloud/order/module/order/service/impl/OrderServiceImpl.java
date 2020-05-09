package com.cloud.order.module.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.serializable.ResultJson;
import com.cloud.order.module.order.entity.Order;
import com.cloud.order.module.order.entity.Payment;
import com.cloud.order.module.order.mapper.OrderMapper;
import com.cloud.order.module.order.service.OrderService;
import com.cloud.order.module.order.service.PaymentFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private PaymentFeignService paymentFeignService;

    @Override
    @GlobalTransactional(name = "order-create-transaction",rollbackFor = Exception.class)
    public boolean create(Order order) {
        orderMapper.create(order);
        ResultJson resultJson = paymentFeignService.create(new Payment(order.getName()));
        ResultJson parse = (ResultJson)JSONObject.parse((String) resultJson.getData());
        if (parse.getCode() == 200){
            return true;
        }
        return false;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }
}
