package com.example.library.Service;

public interface OrderDetailService {
    boolean newOrderDetail(long idOrder , long idProduct , int quantity ,double price);
}
