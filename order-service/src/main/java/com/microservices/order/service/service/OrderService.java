package com.microservices.order.service.service;

import com.microservices.order.service.entity.Order;

public interface OrderService {

    Order placeOrder(Order order);
}
