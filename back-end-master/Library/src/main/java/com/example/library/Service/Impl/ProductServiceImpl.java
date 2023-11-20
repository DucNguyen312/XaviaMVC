package com.example.library.Service.Impl;

import com.example.library.Model.Products;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Products getProductByID(Long id) {
        Optional<Products> products = productRepository.findById(id);
        if (products.isPresent()){
            Products p = products.get();
            return p;
        }
        else
            return null;
    }


}
