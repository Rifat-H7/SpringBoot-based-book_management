package com.example.book_management.service;

import com.example.book_management.domain.Users;
import com.example.book_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String create(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setDateRegistered(LocalDate.now());
        userRepository.save(users);
        return "user added to system";
    }

    public String update(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return "updated";
    }
}
