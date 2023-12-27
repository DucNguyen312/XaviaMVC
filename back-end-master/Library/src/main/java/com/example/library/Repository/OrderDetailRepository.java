package com.example.library.Repository;

import com.example.library.Model.Order;
import com.example.library.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails , Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderDetails od WHERE od.order = :order")
    void deleteOrderDetailsByOrder(@Param("order") Order order);

    List<OrderDetails> findAllByOrder(Order order);

}
