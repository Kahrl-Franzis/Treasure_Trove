package com.asis.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "pirates")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // must match the JSON property names your Angular app sends
    private String username;
    private String email;
    private String passwordHash;
    private String defaultShippingAddress;
}
