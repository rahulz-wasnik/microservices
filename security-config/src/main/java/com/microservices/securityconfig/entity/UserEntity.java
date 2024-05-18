package com.microservices.securityconfig.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQ")
    @SequenceGenerator(name = "USERS_ID_SEQ" ,sequenceName = "USERS_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;
    private String name;
    private String email;
    private String password;
}
