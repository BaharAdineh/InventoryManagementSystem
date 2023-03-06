package com.challenge.ivms.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Order> orders;
}
