package com.microservices.customer.service.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }

    @Column(nullable = false)
    private String firstName;

}

