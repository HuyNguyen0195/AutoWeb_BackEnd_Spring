package com.Huy.AutoWeb.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    private ObjectId id;

    private String username;
    private String password; // In a production scenario, use secure password hashing
    private String email;
    private String fullName;

    // Constructors, getters, and setters
}
