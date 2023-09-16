package com.microservices.order.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderSource;
}
