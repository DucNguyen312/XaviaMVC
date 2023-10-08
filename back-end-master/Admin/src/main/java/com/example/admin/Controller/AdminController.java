package com.example.admin.Controller;

import com.example.library.DTO.UserDTO.LoginDTO;
import com.example.library.DTO.UserDTO.UserDTO;
import com.example.library.Model.Users;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private ResponseEntity<?> Login (@RequestBody LoginDTO loginDTO){
        if(loginDTO == null)
            return ResponseEntity.badRequest().body("Data dont exist");
        Users users = userService.Login(loginDTO);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    private ResponseEntity<String> Register(@RequestBody UserDTO userDTO){
        if (userDTO == null)
            return ResponseEntity.badRequest().body("Data dont exist");
        return ResponseEntity.ok(userService.Register(userDTO));
    }
}
