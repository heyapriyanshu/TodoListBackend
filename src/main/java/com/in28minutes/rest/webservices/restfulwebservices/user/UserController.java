package com.in28minutes.rest.webservices.restfulwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if username already exists
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        // Register user
        User registeredUser = userService.registerUser(user);

        // Return success response
        return ResponseEntity.ok("Registered Successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        Optional<User> optionalUser = userService.findUserByEmail(loginUser.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(loginUser.getPassword())) {
                // Return a response that includes the first name
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("firstName", user.getFirstName());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("Wrong Password");
            }
        } else {
            return ResponseEntity.badRequest().body("User is not present with this email");
        }
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Perform logout actions if needed
        return ResponseEntity.ok("Logout successful");
    }


}