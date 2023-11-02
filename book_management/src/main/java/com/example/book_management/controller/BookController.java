package com.example.book_management.controller;

import com.example.book_management.domain.Book;
import com.example.book_management.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/list")
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String add(@Valid @RequestBody Book book, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        bookRepository.save(book);
        return "Success";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add_collection")
    public String createOrder(@Valid @RequestBody Book book, BindingResult bindingResult) throws SQLException{
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        bookRepository.save(book);
        return "Success";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public String update(@Valid @RequestBody Book book, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        bookRepository.save(book);
        return "Success";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") Long id) throws SQLException {
        bookRepository.deleteById(id);
        return "Success";
    }


}
