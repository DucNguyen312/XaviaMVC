package com.example.library.Service.Impl;

import com.example.library.DTO.Dashboard.DashboardDTO;
import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Repository.CustomerRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.DashboardService;
import com.example.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DashboardDTO getview() {
        List<Order> orders_submit = orderRepository.findByOrderStatus(OrderStatus.SUBMIT);
        List<Order> orders_pending = orderRepository.findByOrderStatus(OrderStatus.PENDING);
        List<Order> orders_cancel = orderRepository.findByOrderStatus(OrderStatus.CANCELLED);
        List<Customer> customerList = customerRepository.findAll();

        double total_today = orderRepository.findRevenueByCurrentDate();
        double total_month = orderRepository.findRevenueByCurrentMonth();
        double total_year = orderRepository.findRevenueByCurrentYear();

        int product_sold = 0;
        for (Order order : orders_submit)
            product_sold += order.getTotal_quantity();

        DashboardDTO dashboardDTO =  new DashboardDTO();
        dashboardDTO.setProductSold(product_sold);
        dashboardDTO.setCustomer(customerList.size());
        dashboardDTO.setOrderSubmit(orders_submit.size());
        dashboardDTO.setOrderPending(orders_pending.size());
        dashboardDTO.setOrderCancel(orders_cancel.size());
        dashboardDTO.setTotalDay(formatCurrency(total_today));
        dashboardDTO.setTotalMonth(formatCurrency(total_month));
        dashboardDTO.setTotalYear(formatCurrency(total_year));

        return dashboardDTO;
    }

    public static String formatCurrency(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###đ");
        return decimalFormat.format(number);
    }
}
