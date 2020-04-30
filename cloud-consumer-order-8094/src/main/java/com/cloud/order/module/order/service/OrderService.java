package com.cloud.order.module.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.order.module.order.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-04-11
 */
public interface OrderService extends IService<Order> {

    boolean create(Order order);

    Order getOrderById(Long id);
}
