package com.example.library.Repository;

import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Long> {

    List<Order> findAllByCustomer(Customer customer);
    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.customer = :customer")
    void deleteOrdersByCustomer(@Param("customer") Customer customer);
}
