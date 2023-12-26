package com.example.admin.Controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/oders")
public class OrderController {

    @GetMapping("")
    public String viewOrder(){
        return "/admin/Oders/ListOrder";
    }
}
