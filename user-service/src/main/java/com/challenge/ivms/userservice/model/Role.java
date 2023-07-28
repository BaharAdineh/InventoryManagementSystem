package com.challenge.ivms.userservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private String name;

}

