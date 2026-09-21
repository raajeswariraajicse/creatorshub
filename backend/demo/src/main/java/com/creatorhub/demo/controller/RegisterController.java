package com.creatorhub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.creatorhub.demo.model.Register;
import com.creatorhub.demo.repository.RegisterRepository;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Register register) {

        // Check duplicate email
        if (registerRepository.findByEmail(register.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email already registered"));
        }

        String password = register.getPassword();

        // Password validation
        if (password == null || password.length() < 8) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message",
                            "Password must be at least 8 characters"));
        }

        if (!password.matches(".*[A-Z].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message",
                            "Password must contain at least one uppercase letter"));
        }

        if (!password.matches(".*[a-z].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message",
                            "Password must contain at least one lowercase letter"));
        }

        if (!password.matches(".*[0-9].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message",
                            "Password must contain at least one number"));
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message",
                            "Password must contain at least one special character"));
        }

        // Encrypt password
        register.setPassword(passwordEncoder.encode(password));

        Register savedRegister = registerRepository.save(register);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Registration successful",
                        "fullName", savedRegister.getFullName(),
                        "email", savedRegister.getEmail()
                ));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Register loginRequest) {

        Register user = registerRepository
                .findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "fullName", user.getFullName(),
                        "email", user.getEmail()
                )
        );
    }
}