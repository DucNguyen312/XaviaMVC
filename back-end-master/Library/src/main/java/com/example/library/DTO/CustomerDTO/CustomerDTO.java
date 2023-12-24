package com.example.library.DTO.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {
    private String fullName;
    private String email;
    private String numberPhone;
    private String address;
    private String note;
    private String paymentMethod;
    private int prePay;

}
