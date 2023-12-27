package com.example.admin.Controller.AdminController;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.Model.Order;
import com.example.library.Model.Users;
import com.example.library.Service.MailService;
import com.example.library.Service.OrderService;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MailService mailService;

    @GetMapping("")
    public String viewOrder(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        List<OrderDTO> orderList = orderService.orderView();
        model.addAttribute("orderList" , orderList);
        model.addAttribute("username", user.getFullName());
        return "/admin/Oders/ListOrder";
    }

    @PostMapping("/submitOrder/{id}")
    public String submitOrder(@PathVariable(name = "id") long id){
        Order order = orderService.updateStatusSubmit(id);
        mailService.sendOrder("Xác nhận Đơn Hàng của Quý Khách tại Xavia " ,order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/cancelOrder/{id}")
    public String cancelOrder(@PathVariable(name = "id") long id){
        Order order =  orderService.updateStatusCancel(id);
        mailService.sendOrder("Thông Báo Hủy Đơn Hàng của Quý Khách tại Xavia" , order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(name = "id") long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}
