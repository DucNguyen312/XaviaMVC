package com.example.library.Service;

import com.example.library.Model.Products;

import java.util.List;

public interface ProductService {
    Products getProductByID(Long id);
    List<Products> getListProduct();

}
