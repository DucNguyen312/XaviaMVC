package com.example.admin.Controller.AdminController;

import com.example.admin.Config.CustomUserConfigService;
import com.example.library.Model.Users;
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

    @GetMapping({"/admin"})
    public String pageAdmin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);
        
        model.addAttribute("username", user.getFullName());
        return "admin/index";
    }

    @GetMapping("/admin/login")
    public String pageLogin() {return "admin/login";}
}
