package com.coin.messages.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String rol; // ROLE_USER, ROLE_ADMIN
}
