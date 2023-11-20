package com.example.admin.Controller.Page;


import com.example.library.DTO.CustomerDTO.CustomerDTO;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Model.Products;
import com.example.library.Service.CustomerService;
import com.example.library.Service.Impl.CartService;

import com.example.library.Service.OrderDetailService;
import com.example.library.Service.OrderService;
import com.example.library.Service.ProductService;
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
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/page/payment" , method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute CustomerDTO customerDTO, Model model){
        Customer customer = customerService.addNewCustomer(customerDTO , 0);
        Order order = orderService.addNewOrder(customer);

        int total_quantity = 0;
        double total_price = 0;
        int total_point = 0;
        List<Product_Items> cart = cartService.viewCart();
        for (Product_Items p : cart){
            orderDetailService.newOrderDetail(order.getId(), p.getId() , p.getSold() , p.getTotal());
            Products product_point = productService.getProductByID(p.getId());
            total_quantity += p.getSold();
            total_price += p.getTotal();
            total_point += product_point.getRewardPoints()*p.getSold();

        }
        orderService.updateOrder(order.getId() , total_quantity , total_price , total_point);
        customerService.addNewCustomer(customerDTO , total_point);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/payment");
        modelAndView.addObject("customerDTO" ,customerDTO);

        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);

        // QR
        String imageUrl = "https://img.vietqr.io/image/Agribank-6600205733805-compact2.jpg?amount="+total_price+"&addInfo=ID "+order.getId()+"&accountName=NGUYEN HUU DUC";
        model.addAttribute("imageUrl", imageUrl);

        //Thanh toán
        String AMOUNT = total_price+"";
        String DESCRIPTION = "ID " + order.getId();
        model.addAttribute("AMOUNT", AMOUNT);
        model.addAttribute("DESCRIPTION", DESCRIPTION);
        return modelAndView;
    }

    @RequestMapping(value = "/page/payment")
    public String home(Model model){
        //Thong tin gio hang
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "/user/payment";
    }
}
