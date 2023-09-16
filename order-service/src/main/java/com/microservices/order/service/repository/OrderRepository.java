package com.microservices.order.service.repository;

import com.microservices.order.service.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
