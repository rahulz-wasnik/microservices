package com.microservices.product.service.entity;

import jakarta.persistence.*;

/**
 * Added here to only understand hibernate N+1 problem and the way to resolve it.
 */

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "QUALITY")
public class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "`QUALITY_ID_SEQ`")
    @SequenceGenerator(name = "QUALITY_ID_SEQ" ,sequenceName = "QUALITY_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "RATING")
    private String rating;
}
