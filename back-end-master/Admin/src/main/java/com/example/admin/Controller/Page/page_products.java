package com.example.admin.Controller.Page;

import com.example.library.Model.Products;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page/product/products")
public class page_products {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String homeProducts(Model model){
        List<Products> listProduct = productService.getListProduct();
        model.addAttribute("listProduct" ,listProduct);
        return  "/user/products";
    }

}
