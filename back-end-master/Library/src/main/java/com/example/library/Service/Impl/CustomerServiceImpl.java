package com.example.library.Service.Impl;

import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.Model.Customer;
import com.example.library.Repository.CustomerRepository;
import com.example.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer addNewCustomer(CustomerDTO customerDTO) {
        boolean exist_customer = customerRepository.existsCustomerByNumberPhone(customerDTO.getNumberPhone());
        if (!exist_customer){
            Customer customer = new Customer();
            customer.setFullName(customerDTO.getFullName());
            customer.setNumberPhone(customerDTO.getNumberPhone());
            customer.setAddress(customerDTO.getAddress());
            customer.setNote(customerDTO.getNote());
            customer.setEmail(customerDTO.getEmail());
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }
}
