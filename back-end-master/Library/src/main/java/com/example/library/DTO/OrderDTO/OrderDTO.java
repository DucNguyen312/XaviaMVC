package com.example.library.DTO.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
public class OrderDTO {
    private long id;
    private Date orderDate;
    private int totalQuantity;
    private String totalPrice;
    private int totalPoint;
    private String paymentMethod;
    private int prePay;
    private String note;
    private String name;
    private String status;
    private ArrayList<OrderDetail_Product> orderDetailProducts;
}
