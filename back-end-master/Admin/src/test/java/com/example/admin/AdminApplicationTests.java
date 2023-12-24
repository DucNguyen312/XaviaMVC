package com.example.admin;

import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Order;
import com.example.library.Model.Products;
import com.example.library.Model.Users;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.*;
import com.example.library.Service.Impl.CartService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminApplicationTests {



}
