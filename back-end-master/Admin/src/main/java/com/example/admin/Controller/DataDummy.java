package com.example.admin.Controller;

import com.example.library.Model.Products;
import com.example.library.Model.Role;
import com.example.library.Model.Users;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.RoleRepository;
import com.example.library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class DataDummy implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        addUser();
        addRole();
        addUser_Role();
        addProduct();
    }


    public void addUser(){
        if(!userRepository.existsByEmail("ADMIN@gmail.com")) {
            Users user = new Users();
            user.setFullName("ADMIN");
            user.setEmail("ADMIN@gmail.com");
            user.setPassword(passwordEncoder.encode("123"));
            userRepository.save(user);
        }
    }

    public void addRole(){
        if (!roleRepository.existsByName("ADMIN")) {
            Role role_admin = new Role();
            role_admin.setName("ADMIN");
            roleRepository.save(role_admin);
        }
        if (!roleRepository.existsByName("USER")){
            Role role_user = new Role();
            role_user.setName("USER");
            roleRepository.save(role_user);
        }
    }

    public void addUser_Role() {
        Users user = userRepository.findByEmail("ADMIN@gmail.com");
        Role role = roleRepository.findByName("ADMIN");
        if (user != null && role != null) {
            Collection<Role> roles = user.getRoles();
            if (roles == null) {
                roles = new HashSet<>();
            }
            if (!roles.contains(role)) {
                roles.add(role);
                user.setRoles(roles);
                userRepository.save(user);
            }
        }
    }

    public void addProduct(){
        Products p1 = new Products();
        p1.setName("Sản phẩm 1");
        p1.setPrice(100000);
        p1.setQuantity(20);
        p1.setSold(0);
        p1.setNote("");
        productRepository.save(p1);
    }




}
