package com.example.library.DTO.ProductsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductView {
    private long id;
    private String name;
    private String price;
    private int quantity;
    private int sold;
    private String material;
    private String note;
    private int rewardPoints;
    private String img;
}
