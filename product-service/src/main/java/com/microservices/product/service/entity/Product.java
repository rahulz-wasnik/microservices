package com.microservices.product.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
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

    @Column(name = "QUANTITY")
    private int quantity;

    /*
     * Added here to only understand hibernate N+1 problem and the way to resolve it.

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUALITY_ID")
    private List<Quality> qualities;

     */
}
