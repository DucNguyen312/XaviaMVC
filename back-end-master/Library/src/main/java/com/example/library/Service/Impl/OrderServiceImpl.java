package com.example.library.Service.Impl;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.DTO.OrderDTO.OrderDetail_Product;
import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Model.OrderDetails;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

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
    public Order updateOrder(long id, int total_quantity, double total_price , int total_point , String paymentMethod , int prePay , String note) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setTotal_quantity(total_quantity);
            order.setTotal_price(total_price);
            order.setTotal_point(total_point);
            order.setPaymentMethod(paymentMethod);
            order.setPrePay(prePay);
            order.setNote(note);
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

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDTO> orderView() {
        List<Order> list = orderRepository.findAll();
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
            orderDTO.setStatus(String.valueOf(order.getOrderStatus()));
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    public static String formatCurrency(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###đ");
        return decimalFormat.format(number);
    }

    @Override
    public Order updateStatusSubmit(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order o = order.get();
            o.setOrderStatus(OrderStatus.SUBMIT);
            return orderRepository.save(o);
        }
        return null;
    }

    @Override
    public Order updateStatusCancel(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order o = order.get();
            o.setOrderStatus(OrderStatus.CANCELLED);
            return orderRepository.save(o);
        }
        return null;
    }

    @Override
    public String deleteOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order order1 = order.get();
            orderDetailRepository.deleteOrderDetailsByOrder(order1);
            orderRepository.delete(order1);
            return "Delete order success";
        }
        return "Delete order fail";
    }
}
