package com.example.library.Service;

import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;

import java.util.List;

public interface MailService {
    String sendMail(Customer customer , Order order , List<Product_Items> product_items);
}
