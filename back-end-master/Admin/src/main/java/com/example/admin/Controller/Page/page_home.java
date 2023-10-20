package com.example.admin.Controller.Page;

import com.example.library.Service.Impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class page_home {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String home(Model model){
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "index";
    }

}
