package com.microservices.order.service.dto;

import lombok.Data;

@Data
public class ProductDto {

    private long id;
    private String name;
    private long quantity;
}
