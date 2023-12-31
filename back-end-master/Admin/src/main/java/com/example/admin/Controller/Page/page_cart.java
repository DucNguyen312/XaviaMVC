package com.example.admin.Controller.Page;

import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Products;
import com.example.library.Service.Impl.CartService;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/page/cart")
public class page_cart {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String Cart(Model model) {
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);

        List<Product_Items> p = cartService.viewCart();
        model.addAttribute("itemCart" , p);
        return "user/cart";
    }

    @GetMapping("/add_product_cart/{productId}")
    private String addProductcarttoHome(@PathVariable("productId") Long id, @RequestParam("action") String action, Model model) {
        boolean result = cartService.addCart(id);
        if ("buy".equals(action)) {
            if (result) {
                model.addAttribute("result_message", "Thêm vào giỏ hàng thành công");

            } else {
                model.addAttribute("result_message", "Sản phẩm đã hết hàng");
            }
        } else if ("cart".equals(action)) {
            if (result) {
                model.addAttribute("result_message", "Thêm vào giỏ hàng thành công");

            } else {
                model.addAttribute("result_message", "Sản phẩm đã hết hàng");
            }
        }

        model.addAttribute("items", cartService.countItemsInCart());
        return "index";
    }

    @GetMapping("/add_product_carts/{productId}")
    private String addProductcarttoProduct(@PathVariable("productId") Long id, @RequestParam("action") String action, Model model) {
        boolean result = cartService.addCart(id);
        if ("buy".equals(action)) {
            if (result) {
                model.addAttribute("result_message", "Thêm vào giỏ hàng thành công");

            } else {
                model.addAttribute("result_message", "Sản phẩm đã hết hàng");
            }
        } else if ("cart".equals(action)) {
            if (result) {
                model.addAttribute("result_message", "Thêm vào giỏ hàng thành công");

            } else {
                model.addAttribute("result_message", "Sản phẩm đã hết hàng");
            }
        }

        List<Products> listProduct = productService.getListProduct();
        model.addAttribute("listProduct" ,listProduct);
        model.addAttribute("items", cartService.countItemsInCart());
        return "/user/products";
    }


}
