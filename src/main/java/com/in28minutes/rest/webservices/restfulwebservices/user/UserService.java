package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User registerUser(User user) {
        // Encode password before saving

        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}