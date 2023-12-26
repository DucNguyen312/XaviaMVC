package com.example.admin;

import com.example.library.DTO.OrderDTO.OrderStatus;
import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Order;
import com.example.library.Model.Products;
import com.example.library.Model.Users;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.*;
import com.example.library.Service.Impl.CartService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminApplicationTests {
    private static final String UPLOAD_FOLDER = "C:\\Users\\PC\\Documents\\Xavia BE\\back-end-master\\Admin\\src\\main\\resources\\static\\image\\products";

    public static boolean checkFileExistence(String folderPath, String fileNameToCheck) {
        File folder = new File(folderPath);

        // Lấy danh sách tập tin trong thư mục
        File[] files = folder.listFiles();

        // Kiểm tra xem có tập tin với tên fileNameToCheck hay không
        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith(fileNameToCheck)) {
                    return file.delete();
                }
            }
        }

        return false;
    }

    @Test
    void Test(){
        boolean isImageExists = checkFileExistence(UPLOAD_FOLDER , "product_38");
        if (isImageExists) {
            System.out.println("Ảnh tồn tại trong thư mục.");
        } else {
            System.out.println("Ảnh không tồn tại trong thư mục.");
        }
    }
}
