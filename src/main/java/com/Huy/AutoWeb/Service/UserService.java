package com.Huy.AutoWeb.Service;

import com.Huy.AutoWeb.Entity.User;
import com.Huy.AutoWeb.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        // Additional validation or business logic can be added here
        return userRepository.save(user);
    }

    public void deleteUser(ObjectId userId) {
        userRepository.deleteById(userId);
    }
}
