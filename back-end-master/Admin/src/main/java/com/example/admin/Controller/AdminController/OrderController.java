package com.example.admin.Controller.AdminController;

import com.example.library.DTO.OrderDTO.OrderDTO;
import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.Model.Order;
import com.example.library.Model.OrderDetails;
import com.example.library.Model.Users;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Service.MailService;
import com.example.library.Service.OrderService;
import com.example.library.Service.ProductService;
import com.example.library.Service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public String viewOrder(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        List<OrderDTO> orderList = orderService.orderView();
        model.addAttribute("orderList" , orderList);
        model.addAttribute("username", user.getFullName());
        return "/admin/Oders/ListOrder";
    }

    @PostMapping("/submitOrder/{id}")
    public String submitOrder(@PathVariable(name = "id") long id){
        Order order = orderService.updateStatusSubmit(id);
        List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.SUBMIT);
        for (Order orders : orderList){
            List<OrderDetails> orderDetailsList = orderDetailRepository.findAllByOrder(orders);
            for (OrderDetails orderDetails : orderDetailsList){
                productService.updateProductSold(orderDetails.getProducts().getId() , orderDetails.getProducts().getSold());
            }
        }
        mailService.sendOrder("Xác nhận Đơn Hàng của Quý Khách tại Xavia " ,order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/cancelOrder/{id}")
    public String cancelOrder(@PathVariable(name = "id") long id){
        Order order =  orderService.updateStatusCancel(id);
        mailService.sendOrder("Thông Báo Hủy Đơn Hàng của Quý Khách tại Xavia" , order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(name = "id") long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}
