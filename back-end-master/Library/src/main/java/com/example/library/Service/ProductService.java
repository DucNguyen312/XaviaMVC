package com.example.library.Service;

import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;

import java.util.List;

public interface ProductService {
    Products getProductByID(Long id);
    List<Products> getListProduct();
    List<ProductView> getListProductView();
    Products newProduct(ProductDTO productDTO);
    String deleteProduct(long id);
    Products updateProduct(long id , ProductDTO productDTO);

    String updateImg(long id , ProductDTO productDTO);
}
