package com.example.library.Service;

import com.example.library.Model.Customer;
import com.example.library.Model.Order;

public interface OrderService {
    Order addNewOrder(Customer customer);
    Order updateOrder(long id , int total_quantity , double total_price , int toltal_point);
}
