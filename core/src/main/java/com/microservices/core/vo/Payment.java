package com.microservices.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Long productId;
    private Long quantity;
    private Long price;
    private String cardNumber;
}
