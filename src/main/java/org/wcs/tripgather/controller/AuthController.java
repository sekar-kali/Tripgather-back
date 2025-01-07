package org.wcs.tripgather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.wcs.tripgather.exception.EmailAlreadyInUseException;
import org.wcs.tripgather.model.User;
import org.wcs.tripgather.service.JwtService;
import org.wcs.tripgather.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        logger.info("Attempting to register user: {}", user);
        try {

            User savedUser = userService.register(
                    user.getEmail(),
                    user.getPassword(),
                    user.getBio(),
                    user.getCountry(),
                    user.getFirstName(),
                    user.getGender(),
                    user.getImageUrl(),
                    user.getLastName(),
                    user.getBirthDate()
            );

            logger.info("User registered successfully: {}", savedUser.getEmail());
            return ResponseEntity.status(201).body(savedUser);
        } catch (EmailAlreadyInUseException e) {
            logger.warn("Email already in use: {}", user.getEmail());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            logger.error("Error during registration: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        try {
            User foundUser = userService.findByEmail(user.getEmail());
            if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                String token = jwtService.generateToken(foundUser);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {

                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Invalid email or password");
                return ResponseEntity.status(401).body(errorResponse);
            }
        } catch (Exception e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error during login: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}