package com.example.library.Repository;

import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products , Long> {
    Optional<Products> findById (Long id);
    boolean existsByName(String name);
    @Query("SELECT p FROM Products p WHERE p.id >= 9")
    List<Products> listProducts();

    @Query("SELECT p FROM Products p WHERE p.id = (SELECT MAX(p2.id) FROM Products p2)")
    Products findProductWithMaxId();


}
