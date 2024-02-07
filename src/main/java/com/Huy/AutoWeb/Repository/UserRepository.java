package com.Huy.AutoWeb.Repository;

import com.Huy.AutoWeb.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    // Add additional custom queries if needed
}

