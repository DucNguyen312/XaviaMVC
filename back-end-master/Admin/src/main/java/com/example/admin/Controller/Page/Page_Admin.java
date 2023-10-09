package com.example.admin.Controller.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Page_Admin {
    @GetMapping("")
    public String showadmin(){return "admin/index";}
}
