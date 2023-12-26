package com.example.library.Service.Impl;

import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.DTO.CustomerDTO.CustomerStatus;
import com.example.library.Model.Customer;
import com.example.library.Model.Products;
import com.example.library.Repository.CustomerRepository;
import com.example.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
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
            customerRepository.delete(customer);
            return "Delete customer success";
        }
        return "Delete customer fail";
    }

    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }
}
