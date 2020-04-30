package com.cloud.order.module.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.order.module.order.entity.Order;
import com.cloud.order.module.order.mapper.OrderMapper;
import com.cloud.order.module.order.service.OrderService;
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

    @Override
    public boolean create(Order order) {
        return orderMapper.create(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }
}
