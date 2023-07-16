package com.challenge.ivms.userservice.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
