package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.repository.UserRepository;
import com.example.bookshop.data.struct.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(int userId) {
        return userRepository.getUserEntityById(userId);
    }

    public UserEntity getUserByName(String username) {
        return userRepository.getUserEntityByName(username);
    }

    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.getUserEntityByEmail(email);
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }
}
