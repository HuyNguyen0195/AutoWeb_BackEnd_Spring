package com.Huy.AutoWeb.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cars") // Specify the MongoDB collection name
public class Car {
    private ObjectId id;

    private String make;
    private String model;
    private int year;
    private double price;
    private int mileage;
    private String fuelType;
    private String transmission;
    private String color;
    private String description;
    private String imageUrl;
}