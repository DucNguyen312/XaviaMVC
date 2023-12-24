package com.example.admin.Controller.AdminController;

import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import com.example.library.Model.Users;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.ProductService;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    private static final String UPLOAD_FOLDER = "C:\\Users\\PC\\Documents\\Xavia BE\\back-end-master\\Admin\\src\\main\\resources\\static\\image\\products";


    @GetMapping("/list-product")
    public String viewListProduct(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        List<ProductView> listProduct = productService.getListProductView();
        model.addAttribute("listProduct" ,listProduct);
        model.addAttribute("username", user.getFullName());
        return "/admin/Product/ListProduct";
    }

    @GetMapping("/add-product")
    public String viewAddProduct(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);

        model.addAttribute("username", user.getFullName());
        return "/admin/Product/AddProduct";
    }

    @PostMapping("/add-product")
    public ModelAndView addProduct(@RequestParam(name = "file" , required = false) MultipartFile file ,
                                   @ModelAttribute ProductDTO productDTO , Model model){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/Product/AddProduct");
        modelAndView.addObject("productDTO" ,productDTO);

        Products id = productRepository.findProductWithMaxId();

        String urlImg = "";
        if (file.isEmpty()) {
            urlImg = "";
        }
        else {
            try {
                String fileName = file.getOriginalFilename();
                String extension = fileName.substring(fileName.lastIndexOf("."));
                File uploadFolder = new File(UPLOAD_FOLDER);

                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                String filePath = UPLOAD_FOLDER + File.separator +"product_" + id + extension;
                urlImg = filePath.substring(filePath.indexOf("\\static") + 7);

                File dest = new File(filePath);
                file.transferTo(dest);

                model.addAttribute("message", "File uploaded successfully.");
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload file.");
                e.printStackTrace();
            }
        }

        productDTO.setImg(urlImg);
        productService.newProduct(productDTO);

        model.addAttribute("result_message" , "Thêm sản phẩm thành công");
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id , Model model){
        productService.deleteProduct(id);
        model.addAttribute("result_message" , "Xóa sản phẩm thành công");
        return "/admin/Product/ListProduct";
    }

}
