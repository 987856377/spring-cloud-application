package com.cloud.order.module.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.order.module.order.entity.Order;
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
public interface OrderMapper extends BaseMapper<Order> {

    boolean create(@Param("mapper/order") Order order);

    Order getOrderById(@Param("id") Long id);
}
