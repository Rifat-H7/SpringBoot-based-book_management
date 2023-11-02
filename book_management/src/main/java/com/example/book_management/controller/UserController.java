package com.example.book_management.controller;

import com.example.book_management.domain.Book;
import com.example.book_management.domain.Users;
import com.example.book_management.dto.AuthRequest;
import com.example.book_management.repository.UserRepository;
import com.example.book_management.service.JwtService;
import com.example.book_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody Users users, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        userService.create(users);
        return "Success";
    }


    @GetMapping("/list")
    public List<Users> list() {
        return userRepository.findAll();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/profile")
    public ResponseEntity<List<Object[]>> userProfile(Authentication authentication) {
        List<Object[]> data = userRepository.findByName(authentication.getName());
        return ResponseEntity.ok(data);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/update")
    public String update(@Valid @RequestBody Users Users, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        userService.update(Users);
        return "Success";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
