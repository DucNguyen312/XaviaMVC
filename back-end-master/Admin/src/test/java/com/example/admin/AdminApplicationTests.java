package com.example.admin;

import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Order;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.Impl.CartService;
import com.example.library.Service.OrderDetailService;
import com.example.library.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminApplicationTests {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;


	@Test
	void contextLoads() {

	}

}
