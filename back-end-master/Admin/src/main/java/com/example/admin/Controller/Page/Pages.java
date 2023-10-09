package com.example.admin.Controller.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class Pages {
    @GetMapping("/user/login")
    public String login(){
        return "user/login";
    }
    @GetMapping("/user/register")
    public String register(){
        return "user/register";
    }
    @GetMapping("/product/products")
    public String products(){
        return "user/products";
    }
    @GetMapping("/product_main/kimcuong")
    public String products_KC(){
        return "user/kimcuong";
    }
    @GetMapping("/product_main/tamsinh")
    public String products_TS(){
        return "user/tamsinh";
    }
    @GetMapping("/product_main/trantinh")
    public String products_TT(){
        return "user/trantinh";
    }
    @GetMapping("/product_main/vinhcuu")
    public String products_VC(){
        return "user/vinhcuu";
    }
    @GetMapping("/gioithieu")
    public String gioithieu() {return "user/gioithieu";}
    @GetMapping("/gift/wedding")
    public String gift_wedding() {return "user/wedding";}
    @GetMapping("/gift/MomDay")
    public String gift_momday() {return "user/Momday";}
    @GetMapping("/contact_us")
    public String Contract_us() {return "user/lienhe";}
    @GetMapping("/comment")
    public String Comment() {return "user/comment";}
    @GetMapping("/cart")
    public String Cart() {return "user/cart";}
    @GetMapping("/blog")
    public String Blog(){return "user/blog";}

}
