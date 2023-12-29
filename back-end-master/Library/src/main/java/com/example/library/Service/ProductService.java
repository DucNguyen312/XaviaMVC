package com.example.library.Service;

import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;

import java.util.List;

public interface ProductService {
    //Lấy Sản phẩm dựa trên ID
    Products getProductByID(Long id);
    //Lấy tất cả danh sách sản phẩm
    List<Products> getListProduct();
    List<ProductView> getListProductView();
    //Tạo sản phẩm mới
    Products newProduct(ProductDTO productDTO);
    //Xóa sản phẩm
    String deleteProduct(long id);
    //Cập nhật sản ph
    Products updateProduct(long id , ProductDTO productDTO);
    String updateImg(long id , ProductDTO productDTO);
    List<ProductView> listProductgetName(String name);
    List<ProductView> listProducts();
    Products updateProductSold(long id , int sold);
}
