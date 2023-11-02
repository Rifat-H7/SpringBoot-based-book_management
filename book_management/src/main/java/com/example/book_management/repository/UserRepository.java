package com.example.book_management.repository;

import com.example.book_management.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users>findByUsername(String username);

    /*@Query("select u from Users  u")
    public List<Users>getusers();*/

    @Query("select u.username, u.fullName, u.dateRegistered, u.email FROM Users u WHERE u.username =:n")
    List<Object[]> findByName(@Param("n") String username);
}
