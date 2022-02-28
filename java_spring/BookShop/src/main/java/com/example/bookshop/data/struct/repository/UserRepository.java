package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity getUserEntityById(int userId);

    UserEntity getUserEntityByName(String username);

    UserEntity getUserEntityByEmail(String email);


}
