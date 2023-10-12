package com.example.library.Service;

import com.example.library.DTO.UserDTO.LoginDTO;
import com.example.library.DTO.UserDTO.UserDTO;
import com.example.library.Model.Users;

public interface UserService {
    String Register(UserDTO userDTO);
    Users Login(LoginDTO loginDTO);
    Users findEmail(String email);
}
