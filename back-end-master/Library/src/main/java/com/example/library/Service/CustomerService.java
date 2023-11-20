package com.example.library.Service;

import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.Model.Customer;

public interface CustomerService {
    Customer addNewCustomer(CustomerDTO customerDTO , int point);

}
