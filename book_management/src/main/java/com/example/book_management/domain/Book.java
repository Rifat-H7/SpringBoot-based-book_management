package com.example.book_management.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 3, message = "minimum 3 character needed")
    private String name;

   /* @ManyToMany
    @JoinTable(
            name = "user_collection",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private List<Users> musers;*/

}
