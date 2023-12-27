package com.example.library.Model;

import com.example.library.DTO.CustomerDTO.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
