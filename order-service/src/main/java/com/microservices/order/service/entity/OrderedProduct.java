package com.microservices.order.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERED_PRODUCT")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERED_PRODUCT_ID_SEQ")
    @SequenceGenerator(name = "ORDERED_PRODUCT_ID_SEQ" ,sequenceName = "ORDERED_PRODUCT_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")
    private Product product;

    @Column(name = "ORDER_QUANTITY")
    private Long orderQuantity;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;
}
