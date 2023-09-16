package com.microservices.order.service.service;

import com.microservices.order.service.connectors.PaymentServiceConnector;
import com.microservices.order.service.connectors.ProductServiceConnector;
import com.microservices.order.service.entity.Order;
import com.microservices.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductServiceConnector productServiceConnector;
    @Autowired
    private PaymentServiceConnector paymentServiceConnector;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order placeOrder(Order order) {
        ResponseEntity<Boolean> inventoryPresent = productServiceConnector.checkQuantity(order.getOrderId());
        ResponseEntity<Boolean> paymentAccepted = paymentServiceConnector.makePayment();
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
