package com.Huy.AutoWeb.Repository;

import com.Huy.AutoWeb.Entity.Car;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, ObjectId> {
    // Add additional custom queries if needed
}
