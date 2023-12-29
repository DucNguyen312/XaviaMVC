package com.example.library.Repository;

import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products , Long> {

    Optional<Products> findById (Long id);
    boolean existsByName(String name);

    //Tìm kiếm danh sách sản phẩm thông qua tên sản phẩm
    @Query("SELECT p FROM Products p where p.name like %:name%")
    List<Products> listProductsByName(@Param("name") String name);

    @Query("SELECT p FROM Products p WHERE p.id >= 7")
    List<Products> listProducts();



}
