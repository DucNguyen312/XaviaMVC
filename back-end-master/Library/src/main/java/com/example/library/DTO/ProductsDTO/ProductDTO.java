package com.example.library.DTO.ProductsDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private int price;
    private int quantity;
    private int sold;
    private String material;
    private String note;
    private int rewardPoints;
    private String img;

}
