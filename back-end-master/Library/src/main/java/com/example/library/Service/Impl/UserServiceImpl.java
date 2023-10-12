package com.example.library.Service.Impl;

import com.example.library.DTO.UserDTO.LoginDTO;
import com.example.library.DTO.UserDTO.UserDTO;
import com.example.library.Model.Users;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String Register(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if (userRepository.existsByEmail(email)){
            return "Email already exist";
        }
        Users user_new = new Users();
        user_new.setFullName(userDTO.getFullName());
        user_new.setEmail(userDTO.getEmail());
        user_new.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user_new);
        return "Register successfully";
    }

    @Override
    public Users Login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        Users user = userRepository.findByEmail(email);
        if (user == null)
            return null;
        boolean result = passwordEncoder.matches(password , user.getPassword());
        if (result)
            return user;
        else
            return null;
    }

    @Override
    public Users findEmail(String email) {
        Users user = userRepository.findByEmail(email);
        return user;
    }
}
