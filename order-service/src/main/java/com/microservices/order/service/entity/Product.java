package com.microservices.order.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Product {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCT_ID_SEQ")
    private Long id;

//    @Column
    private String name;
}
