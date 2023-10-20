package com.example.library.Repository;

import com.example.library.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products , Long> {
    Optional<Products> findById (Long id);
}