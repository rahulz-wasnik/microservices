package com.microservices.order.service.service;

import com.microservices.order.service.bridge.ProductServiceBridge;
import com.microservices.order.service.dto.OrderDto;
import com.microservices.order.service.dto.OrderedProductDto;
import com.microservices.order.service.entity.Order;
import com.microservices.order.service.mapper.OrderMapper;
import com.microservices.order.service.repository.OrderRepository;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceBridge productServiceBridge;
    
    public Order placeOrder(OrderDto orderDto) {
        Predicate<OrderedProductDto> isProductInStock = (orderedProductDto) -> productServiceBridge.isSufficientQuantityPresent(orderedProductDto.getProductId());
        boolean canOrderBeFulfilled = orderDto.getOrderedProducts().stream().allMatch(isProductInStock);

        if (!canOrderBeFulfilled) {
            logger.info("This order cannot be fulfilled since there are no sufficent products in stock");
            // Ideally throw an exception
        }

        Order order = orderMapper.mapToOrderEntity(orderDto);
        return orderRepository.save(order);
    }

}
