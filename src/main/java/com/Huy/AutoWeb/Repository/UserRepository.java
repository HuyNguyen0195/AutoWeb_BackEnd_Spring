package com.Huy.AutoWeb.Repository;

import com.Huy.AutoWeb.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    // Add additional custom queries if needed
}

