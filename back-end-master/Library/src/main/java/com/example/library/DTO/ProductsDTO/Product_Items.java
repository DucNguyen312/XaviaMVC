package com.example.library.DTO.ProductsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Product_Items {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private int sold;
    private double total;
}
