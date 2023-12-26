package com.example.admin.Controller.AdminController;

import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Customer;
import com.example.library.Model.Users;
import com.example.library.Service.CustomerService;
import com.example.library.Service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String view(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        List<Customer> listCustomer = customerService.listCustomer();
        model.addAttribute("listCustomer" , listCustomer);
        model.addAttribute("username", user.getFullName());
        return "/admin/Customer/ListCustomer";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") long id){
        customerService.deleteCustomer(id);
        return "/admin/Customer/ListCustomer";
    }
}
