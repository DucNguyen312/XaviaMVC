package com.example.library.Repository;

import com.example.library.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Long> {
    boolean existsCustomerByNumberPhone(String numberphone);
    Customer findByNumberPhone(String numberphone);

}
