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
    void S(){
        List<Order> list = orderService.listOrder();
        for (Order order : list){
            List<OrderDetails> orderDetailsList = orderDetailRepository.findAllByOrder(order);
            for (OrderDetails orderDetails : orderDetailsList)
                System.out.println(orderDetails);
        }
    }

    @Test
    void Test1(){
        List<Order> list = orderService.listOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : list){
            List<OrderDetails> orderDetailsList = orderDetailRepository.findAllByOrder(order);
            ArrayList<OrderDetail_Product> orderDetailProductArrayList = new ArrayList<>();
            for (OrderDetails orderDetails : orderDetailsList){
                OrderDetail_Product orderDetailProduct = new OrderDetail_Product();
                orderDetailProduct.setId(orderDetails.getId());
                orderDetailProduct.setName(orderDetails.getProducts().getName());
                orderDetailProduct.setQuantity(orderDetails.getQuantity());
                orderDetailProduct.setPrice(formatCurrency(orderDetails.getProducts().getPrice()));
                orderDetailProduct.setUnitPrice(formatCurrency((int) orderDetails.getUnitPrice()));
                orderDetailProductArrayList.add(orderDetailProduct);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setTotalQuantity(order.getTotal_quantity());
            orderDTO.setTotalPrice(formatCurrency((int) order.getTotal_price()));
            orderDTO.setTotalPoint(order.getTotal_point());
            orderDTO.setPaymentMethod(order.getPaymentMethod());
            orderDTO.setPrePay(order.getPrePay());
            orderDTO.setNote(order.getNote());
            orderDTO.setName(order.getCustomer().getFullName());
            orderDTO.setOrderDetailProducts(orderDetailProductArrayList);
            orderDTOList.add(orderDTO);
        }
        for (OrderDTO orderDTO : orderDTOList)
                System.out.println(orderDTO);
    }


    public static String formatCurrency(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###đ");
        return decimalFormat.format(number);
    }




}
