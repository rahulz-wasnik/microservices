package com.microservices.order.service.bridge;

import com.microservices.order.service.connectors.ProductServiceConnector;
import com.microservices.order.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductServiceBridge {

    @Autowired
    private ProductServiceConnector productServiceConnector;

    public boolean isSufficientQuantityPresent(Long productId) {
        ResponseEntity<ProductDto> response = productServiceConnector.getProductQuantity(productId);
        ProductDto productDto = response.getBody();
        if (response.getStatusCode() != HttpStatus.OK || Objects.isNull(productDto)) {
            return false;
        }
        System.out.println("Product quantity - "+ productDto.getQuantity());
        return productDto.getQuantity() > 0;
    }
}
