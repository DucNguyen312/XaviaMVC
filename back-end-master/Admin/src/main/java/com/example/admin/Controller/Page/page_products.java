package com.example.admin.Controller.Page;

import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import com.example.library.Service.Impl.CartService;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page/product/products")
public class page_products {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping("/{products}")
    public String searchProducts(@PathVariable(value = "products") String products, Model model){

        List<ProductView> listProduct = productService.listProductgetName(products);
        int quantityProduct = listProduct.size();
        model.addAttribute("quantityProduct" , quantityProduct);
        model.addAttribute("listProduct" ,listProduct);
        model.addAttribute("items", cartService.countItemsInCart());
        return  "/user/products";
    }

    @GetMapping("")
    public String homeProducts(Model model){
       List<ProductView> listProduct = productService.getListProductView();
       int quantityProduct = listProduct.size();
       model.addAttribute("quantityProduct" , quantityProduct);
       model.addAttribute("listProduct" ,listProduct);
       model.addAttribute("items", cartService.countItemsInCart());
       return  "/user/products";
    }

}
