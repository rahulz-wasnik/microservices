package com.microservices.order.service.mapper;

import com.microservices.order.service.dto.OrderDto;
import com.microservices.order.service.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private OrderedProductMapper orderedProductMapper;

    public Order mapToOrderEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderBy(orderDto.getUserId());
        order.setOrderedProducts(orderDto.getOrderedProducts().stream().map(product -> orderedProductMapper.mapToOrderedProductEntity(product)).toList());
        return order;
    }
}
