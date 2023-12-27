package com.example.admin.Controller.AdminController;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.Model.Order;
import com.example.library.Model.Users;
import com.example.library.Service.OrderService;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/oders")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

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

}
