package com.example.admin.Controller.AdminController;

import com.example.library.DTO.ProductsDTO.ProductDTO;
import com.example.library.DTO.ProductsDTO.ProductView;
import com.example.library.Model.Products;
import com.example.library.Model.Users;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.ProductService;
import com.example.library.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

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

        Products p = productService.newProduct(productDTO);

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

                String filePath = UPLOAD_FOLDER + File.separator +"product_" + p.getId() + extension;
                urlImg = filePath.substring(filePath.indexOf("\\static") + 7);
                urlImg = urlImg.replace("\\" , "/");

                //Tên file
                Path path = FileSystems.getDefault().getPath(filePath);
                String files = path.getFileName().toString();
                System.out.println("Tên tập tin: " + files);

                File dest = new File(filePath);
                file.transferTo(dest);

                model.addAttribute("message", "File uploaded successfully.");
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload file.");
                e.printStackTrace();
            }
        }

        productDTO.setImg(urlImg);
        productService.updateImg(p.getId() , productDTO);

        model.addAttribute("result_message" , "Thêm sản phẩm thành công");
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id , Model model){
        productService.deleteProduct(id);
        checkFileExistence(UPLOAD_FOLDER , "product_" + id);
        model.addAttribute("result_message" , "Xóa sản phẩm thành công");
        return "/admin/Product/ListProduct";
    }

    @PostMapping(value = "/update")
    public ModelAndView updateProduct(@RequestParam(name = "file" , required = false) MultipartFile file ,
                                      @ModelAttribute ProductDTO productDTO , Model model) {

        // Lấy dữ liệu
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/product/list-product");
        modelAndView.addObject("productDTO" ,productDTO);

        Products p = productService.updateProduct(productDTO.getId() , productDTO);
        //Xử lý file ảnh
        String urlImg = "";
        if (file.isEmpty()) {

        }
        else {
            try {
                String fileName = file.getOriginalFilename();
                String extension = fileName.substring(fileName.lastIndexOf("."));
                File uploadFolder = new File(UPLOAD_FOLDER);

                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                String filePath = UPLOAD_FOLDER + File.separator +"product_" + productDTO.getId() + extension;
                urlImg = filePath.substring(filePath.indexOf("\\static") + 7);
                urlImg = urlImg.replace("\\" , "/");


                if(checkFileExistence(UPLOAD_FOLDER , "product_" + p.getId())){
                    File dest = new File(filePath);
                    file.transferTo(dest);
                }
                else{
                    File dest = new File(filePath);
                    file.transferTo(dest);
                }

                productDTO.setImg(urlImg);
                productService.updateImg(p.getId() , productDTO);

                model.addAttribute("message", "File uploaded successfully.");
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload file.");
                e.printStackTrace();
            }
        }




        // Lấy tên admin
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findEmail(email);
        model.addAttribute("username", user.getFullName());

        //Load sản phẩm
        List<ProductView> listProduct = productService.getListProductView();
        model.addAttribute("listProduct" ,listProduct);
        model.addAttribute("result_message" , "Cập nhật sản phẩm thành công");
        return modelAndView;
    }

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

    public static boolean isImageExists(String imageName) {
        String imagePath = UPLOAD_FOLDER + File.separator + imageName;
        File imageFile = new File(imagePath);
        return imageFile.exists();
    }






}
