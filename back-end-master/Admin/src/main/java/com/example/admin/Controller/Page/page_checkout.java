package com.example.admin.Controller.Page;

import com.example.library.Service.Impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/checkout")
public class page_checkout {
    @Autowired
    private CartService cartService;
    @GetMapping("")
    public String home(Model model){
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "/user/checkout";
    }
}
