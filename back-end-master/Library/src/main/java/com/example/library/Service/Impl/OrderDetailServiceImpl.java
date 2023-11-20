package com.example.library.Service.Impl;

import com.example.library.Model.Order;
import com.example.library.Model.OrderDetails;
import com.example.library.Model.Products;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public boolean newOrderDetail(long idOrder, long idProduct, int quantity, double price) {
        Optional<Products> optionalProducts = productRepository.findById(idProduct);
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        if(optionalProducts.isPresent() && optionalOrder.isPresent()){
            Products product = optionalProducts.get();
            Order order = optionalOrder.get();

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProducts(product);
            orderDetails.setQuantity(quantity);
            orderDetails.setUnitPrice(price);
            orderDetailRepository.save(orderDetails);
            return true;
        }
        return false;
    }
}
