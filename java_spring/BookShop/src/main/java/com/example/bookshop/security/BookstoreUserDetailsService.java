package com.example.bookshop.security;

import com.example.bookshop.data.struct.repository.UserRepository;
import com.example.bookshop.data.struct.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookstoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BookstoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserEntityByEmail(s);
        if (user != null) {
            return new BookstoreUserDetails(user);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
