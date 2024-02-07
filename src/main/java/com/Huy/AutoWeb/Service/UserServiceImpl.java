package com.Huy.AutoWeb.Service;

import com.Huy.AutoWeb.Entity.User;
import com.Huy.AutoWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String userId,User editedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            // Update the existing user with the fields from updatedUser
            existingUser.setUsername(editedUser.getUsername());
            existingUser.setEmail(editedUser.getEmail());
            existingUser.setFullName(editedUser.getFullName());
            // Update other fields as needed
            userRepository.save(existingUser);
            return existingUser;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        // Additional validation or business logic can be added here
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


    public boolean checkPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password))
            return true;
        }
        return false;
    }
}
