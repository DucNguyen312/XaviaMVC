package com.example.library.Repository;

import com.example.library.DTO.OrderDTO.OrderStatus;
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

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    // Lấy ra doanh thu của ngày hiện tại
    @Query("SELECT SUM(o.total_price) FROM Order o WHERE DATE(o.orderDate) = CURRENT_DATE AND o.orderStatus = 'SUBMIT'")
    Double findRevenueByCurrentDate();

    // Lấy ra doanh thu của tháng hiện tại
    @Query("SELECT SUM(o.total_price) FROM Order o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE) AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) AND o.orderStatus = 'SUBMIT'")
    Double findRevenueByCurrentMonth();

    // Lấy ra doanh thu của năm hiện tại
    @Query("SELECT SUM(o.total_price) FROM Order o WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE) AND o.orderStatus = 'SUBMIT'")
    Double findRevenueByCurrentYear();

}
