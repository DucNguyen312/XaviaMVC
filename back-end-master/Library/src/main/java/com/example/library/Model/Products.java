package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data @NoArgsConstructor @AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String name;
    private int price;
    private int quantity;
    private int sold;
    private String note;
    private int rewardPoints;
    private String img;

}
