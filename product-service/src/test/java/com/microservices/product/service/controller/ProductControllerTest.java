package com.microservices.product.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void it_should_get_all_products() throws Exception {

        Mockito.when(productService.getAllProducts()).thenReturn(List.of(Product.builder().id(1L).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.equalTo(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void it_should_save_a_product() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Product.builder().name("name").build()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void it_should_update_a_product() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Product.builder().name("name").build()))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                        .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void it_should_delete_a_product() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isAccepted()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void it_should_get_product_by_name() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/products/name").param("name", "Test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.equalTo("Test")))
                .andDo(MockMvcResultHandlers.print());
    }
}