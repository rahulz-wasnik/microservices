package com.microservices.order.service.service;

import com.microservices.order.service.connectors.PaymentServiceConnector;
import com.microservices.order.service.connectors.ProductServiceConnector;
import com.microservices.order.service.entity.Order;
import com.microservices.order.service.entity.Product;
import com.microservices.order.service.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    private ProductServiceConnector productServiceConnector;
    @Mock
    private PaymentServiceConnector paymentServiceConnector;

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    OrderService orderService = new OrderService();
    @DisplayName("Place order - success scenario")
    @Test
    void testWhenOrderPlaced() {

        Order savedMockOrder = getMockOrderVO();
        savedMockOrder.setId(1L);

        // Mock
        when(productServiceConnector.checkQuantity(anyLong()
            )).thenReturn(new ResponseEntity<Boolean>(true, HttpStatus.OK));
        when(paymentServiceConnector.makePayment()).thenReturn(new ResponseEntity<Boolean>(true, HttpStatus.OK));
        when(orderRepository.save(getMockOrderVO())).thenReturn(savedMockOrder);

        // Actual
//        Order savedOrder = orderService.placeOrder(getMockOrderVO());

        // Verify
        verify(productServiceConnector, times(1)).checkQuantity(anyLong());
        verify(paymentServiceConnector, times(1)).makePayment();

        // Assert
//        assertNotNull(savedOrder);
//        assertNotNull(savedOrder.getId());
    }

    private Order getMockOrderVO() {
        Order savedOrderVO = new Order();
//        savedOrderVO.setProducts(List.of(new Product(1L, "Cooker")));
        return savedOrderVO;
    }


}