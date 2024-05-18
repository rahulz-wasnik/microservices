package com.microservices.order.service.mapper;

import com.microservices.order.service.dto.OrderedProductDto;
import com.microservices.order.service.entity.OrderedProduct;
import com.microservices.order.service.entity.Product;
import com.microservices.order.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderedProductMapper {

    @Autowired
    private ProductService productService;

    public OrderedProduct mapToOrderedProductEntity(OrderedProductDto orderedProductDto) {
        Product product = productService.getProductById(orderedProductDto.getProductId());
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setProduct(product);
        orderedProduct.setOrderQuantity(orderedProductDto.getOrderQuantity());
        return orderedProduct;
    }
}
