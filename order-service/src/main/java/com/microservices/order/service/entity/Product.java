package com.microservices.order.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_ID_SEQ")
    @SequenceGenerator(name = "PRODUCTS_ID_SEQ" ,sequenceName = "PRODUCTS_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
