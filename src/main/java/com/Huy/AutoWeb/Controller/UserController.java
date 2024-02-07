package com.Huy.AutoWeb.Controller;

import com.Huy.AutoWeb.Entity.User;
import com.Huy.AutoWeb.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/{userId}")
    public  ResponseEntity<User> saveUser(@PathVariable String userId,
                                          @RequestBody User editedUser){
        System.out.println(editedUser);
        User updateUser= userService.saveUser(userId,editedUser);
        if(updateUser != null){
            return ResponseEntity.ok(updateUser);
        }else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/check-password")
    public ResponseEntity<Map<String,Object>> checkPassword(@RequestBody User user) {
        System.out.println(user);
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, Object> response = new HashMap<>();
        if (userService.checkPassword(username, password)) {
                response.put("success", true);
                response.put("message", "Login successful");
            } else {
                response.put("success", false);
                response.put("message", "Invalid credentials");
            }

            return ResponseEntity.ok(response);
    }
}

