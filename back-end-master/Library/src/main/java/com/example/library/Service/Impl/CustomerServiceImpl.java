package com.example.library.Service.Impl;

import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.DTO.CustomerDTO.CustomerStatus;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Model.Products;
import com.example.library.Repository.CustomerRepository;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Customer addNewCustomer(CustomerDTO customerDTO , int point) {
        boolean exist_customer = customerRepository.existsCustomerByNumberPhone(customerDTO.getNumberPhone());
        if (!exist_customer){
            Customer customer = new Customer();
            customer.setFullName(customerDTO.getFullName());
            customer.setNumberPhone(customerDTO.getNumberPhone());
            customer.setAddress(customerDTO.getAddress());
            customer.setEmail(customerDTO.getEmail());
            customer.setAccumulatedPoints(0);
            customer.setCustomerStatus(CustomerStatus.NORMAL);
            customerRepository.save(customer);
            return customer;
        }
        else {
            Customer customer = customerRepository.findByNumberPhone(customerDTO.getNumberPhone());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            customer.setAccumulatedPoints(customer.getAccumulatedPoints() +  point);
            customerRepository.save(customer);
            return customer;
        }
    }

    @Override
    public String deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            List<Order> orders = orderRepository.findAllByCustomer(customer);
            for (Order order : orders){
                orderDetailRepository.deleteOrderDetailsByOrder(order);
            }
            orderRepository.deleteOrdersByCustomer(customer);
            customerRepository.delete(customer);
            return "delete customer success";
        }
        return "delete customer fail";
    }

    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void updateStatusCustomer() {
        List<Customer> list = customerRepository.findAll();
        for (Customer customer : list){
            if (customer.getAccumulatedPoints() > 1000){
                customer.setCustomerStatus(CustomerStatus.VIP);
                customerRepository.save(customer);
            }
        }
    }
}
