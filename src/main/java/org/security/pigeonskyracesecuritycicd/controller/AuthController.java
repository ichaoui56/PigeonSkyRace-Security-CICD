package org.security.pigeonskyracesecuritycicd.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.security.pigeonskyracesecuritycicd.dto.user.RequestUserDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.User;
import org.security.pigeonskyracesecuritycicd.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RequestUserDTO userDTO) {
        try {
            User user = userService.register(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return "User logged in successfully: " + authentication.getName();
        }
        return "User is not authenticated";
    }
}