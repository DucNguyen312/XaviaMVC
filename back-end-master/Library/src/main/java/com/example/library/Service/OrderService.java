package com.example.library.Service;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;

import java.util.List;

public interface OrderService {
    Order addNewOrder(Customer customer);
    Order updateOrder(long id , int total_quantity , double total_price , int toltal_point , String paymentMethod, int prePay , String note);
    Order updateOrderCheck(long id);
    List<Order> listOrder();
    List<OrderDTO> orderView();
}
