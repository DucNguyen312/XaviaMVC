package com.example.library.Repository;

import com.example.library.Model.Products;
import com.example.library.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long > {
    Users findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT p FROM Products p")
    List<Products> allUser();

}
