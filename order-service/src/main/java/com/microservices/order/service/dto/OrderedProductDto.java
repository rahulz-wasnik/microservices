package com.microservices.order.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderedProductDto {

    private Long productId;
    private Long orderQuantity;
}
