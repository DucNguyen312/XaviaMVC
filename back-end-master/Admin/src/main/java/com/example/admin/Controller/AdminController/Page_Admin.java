package com.example.admin.Controller.AdminController;

import com.example.admin.Config.CustomUserConfigService;
import com.example.library.DTO.Dashboard.DashboardDTO;
import com.example.library.Model.Users;
import com.example.library.Repository.CustomerRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.DashboardService;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page_Admin {

    @Autowired
    private UserService userService;
    @Autowired
    private DashboardService dashboardService;

    @GetMapping({"/admin"})
    public String pageAdmin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        DashboardDTO dash = dashboardService.getview();
        
        model.addAttribute("username", user.getFullName());
        model.addAttribute("dash" , dash);
        return "admin/index";
    }

    @GetMapping("/user/login")
    public String login(){
        return "user/login";
    }


}
