package com.example.library.Model;

import com.example.library.DTO.CustomerDTO.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    private String fullName;
    private String email;
    private String numberPhone;
    private String address;
    private int accumulatedPoints;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
}
