package com.example.admin.Controller.AdminController;

import com.example.library.DTO.CustomerDTO.SendMailDTO;
import com.example.library.Model.Customer;
import com.example.library.Model.Users;
import com.example.library.Service.CustomerService;
import com.example.library.Service.MailService;
import com.example.library.Service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MailService mailService;

    @GetMapping("")
    public String view(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);
        customerService.updateStatusCustomer();

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

    @PostMapping("/sendmail")
    public ModelAndView sendMail(Model model , @ModelAttribute SendMailDTO sendMailDTO){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/customer/");
        modelAndView.addObject("sendMail" , sendMailDTO);

        mailService.send(sendMailDTO);
        model.addAttribute("result_message" , "Gửi mail thành công");
        return modelAndView;
    }
}
