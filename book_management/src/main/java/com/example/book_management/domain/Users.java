package com.example.book_management.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min = 4, message = "minimum 4 characters required")
    private String username;
    @NotEmpty
    @Size(min = 5, message = "minimum 5 characters required")
    private String fullName;
    @NotEmpty
    @Email(message = "not valid email")
    private String email;
    @NotNull
    @Size(min= 4, max = 10, message = "4-10 character required")
    private String password;
    private LocalDate dateRegistered;
    private String roles;

    @ManyToMany
    @JoinTable(name = "user_collection",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "book_id")}
    )
    private List<Book> books;
}
