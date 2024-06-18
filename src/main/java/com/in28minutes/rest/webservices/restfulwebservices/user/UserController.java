package com.in28minutes.rest.webservices.restfulwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
        return ResponseEntity.created(URI.create("/api/users/" + registeredUser.getId())).build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        if (userService.findUserByEmail(loginUser.getEmail()).isPresent()) {
           User user =  userService.findUserByEmail(loginUser.getEmail()).get();
           if(user.getPassword().equals(loginUser.getPassword())) return ResponseEntity.ok("Login successful");
           else return ResponseEntity.badRequest().body("Wrong Password");
        } else{
            return ResponseEntity.badRequest().body("User is not present with this email");
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Perform logout actions if needed
        return ResponseEntity.ok("Logout successful");
    }


}