package com.example.library.DTO.Dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DashboardDTO {
    private int productSold;
    private int customer;
    private int orderSubmit;
    private int orderPending;
    private int orderCancel;
    private String totalDay;
    private String totalMonth;
    private String totalYear;

}
