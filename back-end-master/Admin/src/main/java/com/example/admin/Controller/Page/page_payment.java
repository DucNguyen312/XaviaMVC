package com.example.admin.Controller.Page;


import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Service.CustomerService;
import com.example.library.Service.Impl.CartService;

import com.example.library.Service.OrderDetailService;
import com.example.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class page_payment {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/page/saveCustomer" , method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute CustomerDTO customerDTO){
        Customer customer = customerService.addNewCustomer(customerDTO);
        Order order = orderService.addNewOrder(customer);
        int total_quantity = 0;
        double total_price = 0;

        List<Product_Items> cart = cartService.viewCart();
        for (Product_Items p : cart){
            orderDetailService.newOrderDetail(order.getId(), p.getId() , p.getSold() , p.getTotal());
            total_quantity += p.getSold();
            total_price += p.getTotal();

        }
        orderService.updateOrder(order.getId() , total_quantity , total_price);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");  //sau khi post xong sẽ trả về trang này
        modelAndView.addObject("customerDTO" ,customerDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/page/payment")
    public String home(Model model){
        //Thong tin gio hang
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "/index";
    }
}
