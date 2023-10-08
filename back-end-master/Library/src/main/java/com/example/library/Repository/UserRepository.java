package com.example.library.Repository;

import com.example.library.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long > {
    Users findByEmail(String email);
    boolean existsByEmail(String email);

}
