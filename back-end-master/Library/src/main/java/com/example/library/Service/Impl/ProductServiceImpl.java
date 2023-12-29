package com.example.library.Service.Impl;

import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    //Lấy Product dựa trên ID
    @Override
    public Products getProductByID(Long id) {
        Optional<Products> products = productRepository.findById(id);
        if (products.isPresent()){
            Products p = products.get();
            return p;
        }
        else
            return null;
    }

    @Override
    public List<Products> getListProduct() {
        return productRepository.listProducts();
    }

    @Override
    public Products newProduct(ProductDTO productDTO) {
        Products products = new Products();
        products.setName(productDTO.getName());
        products.setPrice(productDTO.getPrice());
        products.setQuantity(productDTO.getQuantity());
        products.setSold(0);
        products.setMaterial(productDTO.getMaterial());
        products.setRewardPoints(productDTO.getRewardPoints());
        products.setNote(productDTO.getNote());
        return productRepository.save(products);
    }

    @Override
    public String deleteProduct(long id) {
        Optional<Products> optionalProducts = productRepository.findById(id);
        if (optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            productRepository.delete(products);
            return "Delete product success";
        }
        return "Delete product fail";
    }

    @Override
    public Products updateProduct(long id ,ProductDTO productDTO) {
        Optional<Products> optionalProducts = productRepository.findById(id);
        if (optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setName(productDTO.getName());
            products.setPrice(productDTO.getPrice());
            products.setQuantity(productDTO.getQuantity());
            products.setSold(productDTO.getSold());
            products.setMaterial(productDTO.getMaterial());
            products.setRewardPoints(productDTO.getRewardPoints());
            products.setNote(productDTO.getNote());
            return productRepository.save(products);
        }
        return null;
    }

    @Override
    public String updateImg(long id, ProductDTO productDTO) {
        Optional<Products> optionalProducts = productRepository.findById(id);
        if(optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setImg(productDTO.getImg());
            productRepository.save(products);
            return "Update image success";
        }
        return null;
    }

    @Override
    public List<ProductView> getListProductView() {
        List<Products> productsList = productRepository.listProducts();
        List<ProductView> productViewList = new ArrayList<>();
        for(Products p : productsList){
            ProductView productView = new ProductView();
            productView.setId(p.getId());
            productView.setName(p.getName());
            String price = formatCurrency(p.getPrice());
            productView.setPrice(price);
            productView.setQuantity(p.getQuantity());
            productView.setSold(p.getSold());
            productView.setMaterial(p.getMaterial());
            productView.setNote(p.getNote());
            productView.setRewardPoints(p.getRewardPoints());
            productView.setImg(p.getImg());
            productViewList.add(productView);
        }
        return productViewList;
    }

    public static String formatCurrency(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###đ");
        return decimalFormat.format(number);
    }

    @Override
    public List<ProductView> listProductgetName(String name) {
        List<Products> productsList = productRepository.listProductsByName(name);
        List<ProductView> productViewList = new ArrayList<>();
        for(Products p : productsList){
            ProductView productView = new ProductView();
            productView.setId(p.getId());
            productView.setName(p.getName());
            String price = formatCurrency(p.getPrice());
            productView.setPrice(price);
            productView.setQuantity(p.getQuantity());
            productView.setSold(p.getSold());
            productView.setMaterial(p.getMaterial());
            productView.setNote(p.getNote());
            productView.setRewardPoints(p.getRewardPoints());
            productView.setImg(p.getImg());
            productViewList.add(productView);
        }
        return productViewList;
    }

    @Override
    public List<ProductView> listProducts() {
        List<Products> productsList = productRepository.findAll();
        List<ProductView> productViewList = new ArrayList<>();
        for(Products p : productsList){
            ProductView productView = new ProductView();
            productView.setId(p.getId());
            productView.setName(p.getName());
            String price = formatCurrency(p.getPrice());
            productView.setPrice(price);
            productView.setQuantity(p.getQuantity());
            productView.setSold(p.getSold());
            productView.setMaterial(p.getMaterial());
            productView.setNote(p.getNote());
            productView.setRewardPoints(p.getRewardPoints());
            productView.setImg(p.getImg());
            productViewList.add(productView);
        }
        return productViewList;
    }

    @Override
    public Products updateProductSold(long id, int sold) {
        Optional<Products> optionalProducts = productRepository.findById(id);
        if (optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setSold(products.getSold() + sold);
            products.setQuantity(products.getQuantity() - sold);
            return productRepository.save(products);
        }
        return null;
    }
}
