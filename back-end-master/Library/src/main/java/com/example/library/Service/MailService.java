package com.example.library.Service;

import com.example.library.DTO.CustomerDTO.SendMailDTO;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;

import java.util.List;

public interface MailService {
    String sendMail(Customer customer , Order order , List<Product_Items> product_items);
    String send(SendMailDTO sendMailDTO);
    String sendOrder(String subject ,Order order);
}
