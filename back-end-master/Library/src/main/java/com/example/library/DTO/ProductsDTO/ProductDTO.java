package com.example.library.DTO.ProductsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private String name;
    private int price;
    private int quantity;
    private String material;
    private String note;
    private int rewardPoints;
    private String img;
}
