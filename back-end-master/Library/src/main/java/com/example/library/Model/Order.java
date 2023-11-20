package com.example.library.Model;

import com.example.library.DTO.OrderDTO.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data @NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    private Date orderDate;
    private int total_quantity;
    private double total_price;
    private int total_point;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
