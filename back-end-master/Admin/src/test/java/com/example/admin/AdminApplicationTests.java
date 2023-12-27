package com.example.admin;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.DTO.OrderDTO.OrderDetail_Product;
import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.*;
import com.example.library.Repository.*;
import com.example.library.Service.*;
import com.example.library.Service.Impl.CartService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;


    @Test
    void Test(){
        List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.SUBMIT);
        for (Order order : orders)
            System.out.println(order.getTotal_price());
    }







}
