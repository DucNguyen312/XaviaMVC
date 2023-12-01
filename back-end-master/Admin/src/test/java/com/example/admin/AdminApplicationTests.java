package com.example.admin;

import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Order;
import com.example.library.Model.Products;
import com.example.library.Model.Users;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.Impl.CartService;
import com.example.library.Service.OrderDetailService;
import com.example.library.Service.OrderService;
import com.example.library.Service.ProductService;
import com.example.library.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void Test(){
        List<Products> users = userRepository.allUser();
        for (Products user: users) {
            System.out.println(user);
        }
    }

}
