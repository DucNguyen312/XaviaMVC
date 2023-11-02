package com.example.library.Repository;

import com.example.library.Model.Order;
import com.example.library.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetails , Long> {
}
