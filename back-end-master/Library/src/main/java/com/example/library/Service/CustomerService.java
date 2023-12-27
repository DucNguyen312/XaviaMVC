package com.example.library.Service;

import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.Model.Customer;

import java.util.List;

public interface CustomerService {
    Customer addNewCustomer(CustomerDTO customerDTO , int point);
    List<Customer> listCustomer();
    String deleteCustomer(long id);

    void updateStatusCustomer();
}
