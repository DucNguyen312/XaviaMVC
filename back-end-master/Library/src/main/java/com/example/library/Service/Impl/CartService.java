package com.example.library.Service.Impl;

import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Products;
import com.example.library.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    public ArrayList<Product_Items> cart = new ArrayList<>();
    public boolean addCart(long id) {
        Optional<Products> optionalProducts = productRepository.findById(id);
        if (optionalProducts.isPresent()) {
            Product_Items products = new Product_Items();
            products.setId(optionalProducts.get().getId());
            products.setName(optionalProducts.get().getName());
            products.setPrice(optionalProducts.get().getPrice());
            products.setQuantity(optionalProducts.get().getQuantity());

            boolean productExistsInCart = false;

            for (Product_Items productItems : cart) {
                if (productItems.getId().equals(products.getId())) {
                    if (productItems.getSold() + 1 <= productItems.getQuantity()) {
                        productItems.setSold(productItems.getSold() + 1);
                        productItems.setTotal(productItems.getSold() * productItems.getPrice());
                        productExistsInCart = true;
                    } else {
                        return false;
                    }
                    break;
                }
            }
            if (!productExistsInCart) {
                if (products.getSold() < products.getQuantity()) {
                    products.setSold(1);
                    products.setTotal(products.getSold() * products.getPrice());
                    cart.add(products);
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int countItemsInCart() {
        return cart.size();
    }


    public List<Product_Items> viewCart() {
        return cart;
    }
}
