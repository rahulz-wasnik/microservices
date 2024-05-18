package com.microservices.order.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_ID_SEQ")
    @SequenceGenerator(name = "ORDERS_ID_SEQ" ,sequenceName = "ORDERS_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "ORDER_BY")
    private String orderBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderedProduct> orderedProducts;
}
