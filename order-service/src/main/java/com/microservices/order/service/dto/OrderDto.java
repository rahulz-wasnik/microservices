package com.microservices.order.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private String userId;
    private List<OrderedProductDto> orderedProducts;
}
