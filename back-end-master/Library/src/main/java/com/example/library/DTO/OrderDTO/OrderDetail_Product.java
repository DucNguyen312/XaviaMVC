package com.example.library.DTO.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDetail_Product {
    private long id;
    private String name;
    private int quantity;
    private String price;
    private String unitPrice;
}
