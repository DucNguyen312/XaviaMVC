package com.example.library.Service.Impl;

import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order addNewOrder(Customer customer) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.CANCELLED);
        order.setOrderDate(new Date());
        order.setCustomer(customer);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(long id, int total_quantity, double total_price , int total_point , String paymentMethod , int prePay) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setTotal_quantity(total_quantity);
            order.setTotal_price(total_price);
            order.setTotal_point(total_point);
            order.setPaymentMethod(paymentMethod);
            order.setPrePay(prePay);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public Order updateOrderCheck(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.PENDING);
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}
